package com.sieun.supporters_android.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityListBinding
import com.sieun.supporters_android.ui.list.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity: AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()
    private var categoryNum = 0
    private var categoryAdapter = CategoryListAdapter()
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        // ToDo categories ID get
        categoryNum = 1

        fetchCategoryItems(categoryNum)
    }

    private fun fetchCategoryItems(categoryNum: Int) {
        viewModel.fetchCategoryItem(categoryNum)
        println("fetchCategoryItems called")
    }


}