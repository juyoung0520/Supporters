package com.sieun.supporters_android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityListBinding
import com.sieun.supporters_android.ui.list.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity: AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()
    private var categoryNum = 0L
    private lateinit var binding: ActivityListBinding
    private lateinit var categoryName: String
    private val categoryListAdapter by lazy { CategoryListAdapter(categoryName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        processIntent()
        fetchCategoryItems(categoryNum)
        setBackButton()
        setRVSetting()
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

    private fun setRVSetting() {
        binding.listContainer.infiniteList.apply {
            adapter = categoryListAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

}