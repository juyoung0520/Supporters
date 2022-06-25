package com.sieun.supporters_android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity: AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()
    private var categoryNum = 0L
    private lateinit var binding: ActivityListBinding
    private lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        processIntent()
        fetchCategoryItems(categoryNum)
        setBackButton()
    }

    private fun fetchCategoryItems(categoryNum: Long) {
        viewModel.fetchCategoryItem(categoryNum)
    }

    private fun setBackButton() {
        binding.listBackbutton.setOnClickListener {
            this.finish()
        }
    }

    private fun processIntent() {
        intent.extras?.let {
            categoryNum = it.getLong(HomeActivity.CATEGORY_ID)
            categoryName = it.getString(HomeActivity.CATEGORY_NAME) ?: "Unknown"
            Glide.with(binding.root)
                .load(it.getString(HomeActivity.CATEGORY_THUMBNAIL))
                .into(binding.categoryThumbnail)
        }
    }

}