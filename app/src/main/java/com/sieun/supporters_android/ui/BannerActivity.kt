package com.sieun.supporters_android.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityBannerBinding
import com.sieun.supporters_android.ui.list.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BannerActivity : AppCompatActivity() {
    private val viewModel: BannerViewModel by viewModels()
    private lateinit var binding: ActivityBannerBinding
    private val categoryListAdapter by lazy { CategoryListAdapter("Campaign") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityBannerBinding>(this, R.layout.activity_banner)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        intent.extras?.let {
            val id = it.getLong(HomeActivity.BANNER_ID)
            viewModel.getBanner(id)
        }

        setRVSetting()
        setBackButton()
    }

    private fun setRVSetting() {
        binding.listContainer.infiniteList.apply {
            adapter = categoryListAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun setBackButton() {
        binding.bannerBackIv.setOnClickListener {
            this.finish()
        }
    }
}