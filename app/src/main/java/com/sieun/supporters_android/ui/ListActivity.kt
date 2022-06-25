package com.sieun.supporters_android.ui

import android.graphics.Outline
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sieun.supporters_android.R
import com.sieun.supporters_android.databinding.ActivityListBinding
import com.sieun.supporters_android.ui.list.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class ListActivity: AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels()
    private var categoryNum = 0L
    private lateinit var binding: ActivityListBinding
    private lateinit var categoryName: String
    private val categoryListAdapter by lazy { CategoryListAdapter(categoryName) }
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
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.categoryThumbnail.apply {
            outlineProvider = this@ListActivity.outlineProvider
            clipToOutline = true
        }

        processIntent()
        fetchCategoryItems(categoryNum)
        setBackButton()
        setRVSetting()
        setChipSelected()
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
            Glide.with(binding.categoryThumbnail)
                .load(it.getString(HomeActivity.CATEGORY_IMG))
                .into(binding.categoryThumbnail)
        }
        binding.categoryName.text = categoryName
        when (categoryName) {
            "환경" -> { binding.categoryDesc.text = "환경을 지키기 위한 노력을 함께하세요"}
            "동물" ->{ binding.categoryDesc.text = "동물을 지키기 위한 노력을 함께하세요"}
            "아동" ->{ binding.categoryDesc.text = "아이들을 지키기 위한 노력을 함께하세요"}
            "여성" ->{ binding.categoryDesc.text = "여성 인권을 지키기 위한 노력을 함께하세요"}
            "자연재해" ->{ binding.categoryDesc.text = "재해로부터 지키기 위한 노력을 함께하세요"}
            else -> { binding.categoryDesc.text = "함께하기 위한 다양한 노력을 함께하세요"}
        }
    }

    private fun setRVSetting() {
        binding.listContainer.infiniteList.apply {
            adapter = categoryListAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun setChipSelected() {
        binding.listChipGroupSetting.setOnCheckedStateChangeListener { _, checkedIds ->
            when(checkedIds.last()) {
                R.id.list_setting_all -> {
                    viewModel.categoryItemsResult.value?.let {
                        categoryListAdapter.submitList((it.items))
                    }
                }
                R.id.list_setting_long -> {
                    viewModel.categoryItemsResult.value?.let { result ->
                        categoryListAdapter.submitList((result.items).filter { it.periodId == 1 })
                    }
                }
                else -> {
                    viewModel.categoryItemsResult.value?.let { result ->
                        categoryListAdapter.submitList((result.items).filter { it.periodId == 2 })
                    }
                }
            }
        }
    }

}