package com.route.legrand.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {
    private var _loadingLiveData = MutableLiveData(false)

    val loadingLiveData get() = _loadingLiveData

    private var _errorLiveData = MutableLiveData(false)
    val errorLiveData get() = _loadingLiveData
}