package com.route.legrand.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.legrand.models.ErrorMessage

open class BaseViewModel : ViewModel() {
     var loadingLiveData = MutableLiveData(false)


    var errorLiveData = MutableLiveData<ErrorMessage>()

}