package com.sieun.supporters_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityWebBinding>(this, R.layout.activity_web)

        intent.getStringExtra(DetailActivity.SITE_URL)
            ?.let {
                binding.web.loadUrl(it)
            }
    }
}