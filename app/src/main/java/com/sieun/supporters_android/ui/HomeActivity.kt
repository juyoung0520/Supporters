package com.sieun.supporters_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityHomeBinding
import com.sieun.supporters_android.model.Banner
import com.sieun.supporters_android.model.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val bannerAdapter by lazy { BannerAdapter(::onClickBanner) }
    private val categoryAdapter by lazy { CategoryAdapter(::onClickCategory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        initView(binding)

        viewModel.getHome()
    }

    private fun initView(binding: ActivityHomeBinding) = with(binding) {
        rvCategory.adapter = categoryAdapter

        val rvBanner = rvBanner.apply { adapter = bannerAdapter }
        val snapHelper = PagerSnapHelper().apply { attachToRecyclerView(rvBanner) }
        indicator.attachToRecyclerView(rvBanner, snapHelper)
        bannerAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
    }

    private fun onClickBanner(banner: Banner) {
        Intent(this, BannerActivity::class.java)
            .putExtra(BANNER_ID, banner.id)
            .let { intent -> startActivity(intent) }
    }

    private fun onClickCategory(category: Category) {
        Intent(this, ListActivity::class.java)
            .putExtra(CATEGORY_ID, category.id)
            .let { intent -> startActivity(intent) }
    }

    companion object {
        const val BANNER_ID = "bannerId"
        const val CATEGORY_ID = "categoryId"
    }
}