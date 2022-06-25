package com.sieun.supporters_android.ui

import androidx.lifecycle.ViewModel
import com.sieun.supporters_android.api.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: ApiRepository
) : ViewModel() {
}