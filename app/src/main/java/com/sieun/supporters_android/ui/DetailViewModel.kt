package com.sieun.supporters_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sieun.supporters_android.api.ApiRepository
import com.sieun.supporters_android.model.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: ApiRepository
) : ViewModel() {
    private val _item = MutableLiveData<CategoryItem>()
    val item: LiveData<CategoryItem>
        get() = _item

    fun getDetailItem(id: Long) = viewModelScope.launch {
        repo.getDetailItem(id).body()
            ?.let { detailResult ->
                _item.value = detailResult.item
            }
    }
}