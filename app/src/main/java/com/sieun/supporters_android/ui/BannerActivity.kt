package com.sieun.supporters_android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityBannerBinding

class BannerActivity : AppCompatActivity() {
    private val viewModel: BannerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityBannerBinding>(this, R.layout.activity_banner)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}