package com.sieun.supporters_android.ui

import android.content.Intent
import android.graphics.Outline
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityHomeBinding
import com.sieun.supporters_android.dpToPx
import com.sieun.supporters_android.model.Banner
import com.sieun.supporters_android.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()
    private val bannerAdapter by lazy { BannerAdapter(::onClickBanner, outlineProvider) }
    private val categoryAdapter by lazy { CategoryAdapter(::onClickCategory, categoryWidth) }
    private val categoryWidth by lazy { (resources.displayMetrics.widthPixels - dpToPx(40)) / 2 }
    private val outlineProvider: ViewOutlineProvider by lazy {
        object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                val cornerRadiusDP = 40f
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    cornerRadiusDP,
                    resources.displayMetrics
                ).let { cornerRadius ->
                    val top = (0 - cornerRadius).roundToInt()
                    outline.setRoundRect(0, top, view.width, view.height, cornerRadius)
                }
            }
        }
    }

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
            .putExtra(CATEGORY_IMG, category.imageUrl)
            .putExtra(CATEGORY_NAME, category.name)
            .let { intent -> startActivity(intent) }
    }

    companion object {
        const val BANNER_ID = "bannerId"
        const val CATEGORY_ID = "categoryId"
        const val CATEGORY_IMG = "categoryImgUrl"
        const val CATEGORY_NAME = "categoryName"
    }
}