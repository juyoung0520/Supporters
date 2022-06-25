package com.sieun.supporters_android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityListBinding

class ListActivity: AppCompatActivity() {
    private val viewModel: ListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_detail)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}