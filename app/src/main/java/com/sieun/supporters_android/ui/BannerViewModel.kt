package com.sieun.supporters_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sieun.supporters_android.api.ApiRepository
import com.sieun.supporters_android.model.Banner
import com.sieun.supporters_android.model.CategoryItem
import com.sieun.supporters_android.model.CategoryItemsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BannerViewModel @Inject constructor(
    private val repo: ApiRepository
) : ViewModel() {
    private val _banner = MutableLiveData<CategoryItem>()
    val banner: LiveData<CategoryItem>
        get() = _banner

    private val _items = MutableLiveData<CategoryItemsResult>(CategoryItemsResult.empty)
    val items: LiveData<CategoryItemsResult>
        get() = _items

    fun getBanner(id: Long) = viewModelScope.launch {
        repo.getBanner(id).body()
            ?.let { bannerResult ->
                _banner.value = bannerResult.banner
                _items.value = CategoryItemsResult(bannerResult.items)
            }
    }

}