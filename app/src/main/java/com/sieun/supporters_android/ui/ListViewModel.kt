package com.sieun.supporters_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sieun.supporters_android.api.ApiRepository
import com.sieun.supporters_android.model.CategoryItemsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: ApiRepository
) : ViewModel() {

    private var _categoryItemsResult: MutableLiveData<CategoryItemsResult> =
        MutableLiveData(CategoryItemsResult.empty)
    val categoryItemsResult: LiveData<CategoryItemsResult> get() = _categoryItemsResult

    fun fetchCategoryItem(categoryNum: Long) {
        viewModelScope.launch {
            val listResult = withContext(Dispatchers.IO) {
                repo.fetchCategoryItem(categoryNum)
            }
            if (listResult.isSuccessful) {
                listResult.body()?.let {
                    _categoryItemsResult.value = it
                }
            }
        }
    }
}