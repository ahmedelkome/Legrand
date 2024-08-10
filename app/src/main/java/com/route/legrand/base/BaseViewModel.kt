package com.route.legrand.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {
    private val _loadingLiveData = MutableLiveData(false)
    val loadingLiveData get() = _loadingLiveData
}