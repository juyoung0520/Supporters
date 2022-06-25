package com.sieun.supporters_android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sieun.supporters_android.api.ApiRepository
import com.sieun.supporters_android.model.Banner
import com.sieun.supporters_android.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ApiRepository
) : ViewModel() {
    private val _category = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>>
        get() = _category

    private val _banner = MutableLiveData<List<Banner>>()
    val banner: LiveData<List<Banner>>
        get() = _banner

    fun getHome() = viewModelScope.launch {
        repo.getHome().body()
            ?.let { home ->
                Log.d("!@#", home.toString())

                _category.value = home.categories
                _banner.value = home.banners
            }
    }
}