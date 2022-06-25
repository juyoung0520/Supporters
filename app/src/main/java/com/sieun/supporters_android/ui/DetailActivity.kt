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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var tagAdapter: TagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        intent.extras?.let {
            //val id = intent.getLongExtra()
            //viewModel.getDetailItem()
        }
        viewModel.getDetailItem(1)
        onClickButton(CategoryItem(1, "d", "d", "https://www.unhcr.or.kr/unhcr/program/donate_row/support01.jsp", ""))
    }

    private fun initView(binding: ActivityDetailBinding) = with(binding) {
        detailTagRv.adapter = tagAdapter
    }

    private fun onClickButton(item: CategoryItem) {
        Intent(this, WebActivity::class.java)
            .putExtra(SITE_URL, item.siteUrl)
            .let { intent -> startActivity(intent) }
    }

    companion object {
        const val SITE_URL = "siteUrl"
    }
}