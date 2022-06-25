package com.sieun.supporters_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityBannerBinding
import com.sieun.supporters_android.databinding.ActivityDetailBinding
import com.sieun.supporters_android.model.CategoryItem
import com.sieun.supporters_android.ui.list.CategoryItemViewHolder.Companion.ITEM_ID
import com.sieun.supporters_android.ui.list.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()
    private val tagAdapter: TagAdapter by lazy { TagAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.vm = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        intent.extras?.let {
            val id = intent.getLongExtra(ITEM_ID, 0L)
            viewModel.getDetailItem(id)
        }

        initView(binding)
    }

    private fun initView(binding: ActivityDetailBinding) = with(binding) {
        detailTagRv.adapter = tagAdapter

        detailBackIv.setOnClickListener {
            this@DetailActivity.finish()
        }
    }

    fun onClickButton(item: CategoryItem) {
        Intent(this, WebActivity::class.java)
            .putExtra(SITE_URL, item.siteUrl)
            .let { intent -> startActivity(intent) }
    }

    companion object {
        const val SITE_URL = "siteUrl"
    }
}