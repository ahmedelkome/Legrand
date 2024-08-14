package com.route.legrand.fragments.production.injection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.injection.InjectionData
import com.route.domain.usecases.injection.InjectionUseCase
import com.route.legrand.base.BaseViewModel
import com.route.legrand.models.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InjectionViewModel @Inject constructor(
    private val injectionUseCase: InjectionUseCase
) : BaseViewModel() {
    var listOfInjectionData = MutableLiveData<List<InjectionData>>()
    private val dispatcher = Dispatchers.IO

    fun getInjectionData() {
        viewModelScope.launch(dispatcher) {
            loadingLiveData.postValue(true)
            injectionUseCase.execute().collect {
                when (it) {
                    is ResultWrapper.Failure -> {

                        errorLiveData.postValue(
                            ErrorMessage(
                                title = "Error",
                                message = it.e.localizedMessage
                            )
                        )
                        loadingLiveData.postValue(false)
                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {

                        listOfInjectionData.postValue(it.data)
                        loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}