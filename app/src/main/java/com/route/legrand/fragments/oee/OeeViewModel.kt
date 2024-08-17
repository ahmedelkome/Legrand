package com.route.legrand.fragments.oee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.oee.OEE
import com.route.domain.usecases.oee.OeeUseCase
import com.route.legrand.base.BaseViewModel
import com.route.legrand.models.ErrorMessage
import com.route.legrand.notification.NotificationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OeeViewModel @Inject constructor(
    private val oeeUseCase: OeeUseCase,
    private val notificationHelper: NotificationHelper
) : BaseViewModel() {
    private val dispatcher = Dispatchers.IO
    var dateLiveData = MutableLiveData<String>()
    var timeLiveData = MutableLiveData<String>()
    var shiftLiveData = MutableLiveData<String>()
    var machineLiveData = MutableLiveData<String>()
    var operatorLiveData = MutableLiveData<String>()
    var partNumberLiveData = MutableLiveData<String>()
    var cavityNumberLiveData = MutableLiveData<String>()
    var totalProducedLiveData = MutableLiveData<String>()
    var workCvLiveData = MutableLiveData<String>()
    var CTLiveData = MutableLiveData<String>()
    var ELiveData = MutableLiveData<String>()
    var MOLiveData = MutableLiveData<String>()
    var HLiveData = MutableLiveData<String>()
    var MELiveData = MutableLiveData<String>()
    var QLiveData = MutableLiveData<String>()
    var DMLiveData = MutableLiveData<String>()
    var COLiveData = MutableLiveData<String>()
    var SULiveData = MutableLiveData<String>()
    var CMLiveData = MutableLiveData<String>()
    var RLiveData = MutableLiveData<String>()
    var WLLiveData = MutableLiveData<String>()
    var OTHLiveData = MutableLiveData<String>()
    var listOfPartNUmber = MutableLiveData<List<String>>()
    var message = MutableLiveData<String>()
    var events = MutableLiveData<OeeEvents>()
    fun getPartNumber() {
        viewModelScope.launch(dispatcher) {
            oeeUseCase.getPartNumber().collect {
                when (it) {
                    is ResultWrapper.Failure -> {
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(
                            ErrorMessage(
                                title = "Error",
                                message = it.e.localizedMessage
                            )
                        )
                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {
                        loadingLiveData.postValue(false)
                        listOfPartNUmber.postValue(it.data)
                    }
                }
            }
        }
    }

    fun postData() {

        viewModelScope.launch(dispatcher) {
            oeeUseCase.postData(
                OEE(
                    Date = dateLiveData.value,
                    Time = timeLiveData.value,
                    Shift = shiftLiveData.value,
                    Machine = machineLiveData.value,
                    OperatorCode = operatorLiveData.value,
                    PartNumber = partNumberLiveData.value,
                    cavityNumber = cavityNumberLiveData.value,
                    TotalProduced = totalProducedLiveData.value,
                    WorkCav = workCvLiveData.value,
                    CT = CTLiveData.value,
                    E = ELiveData.value,
                    MO = MOLiveData.value,
                    H = HLiveData.value,
                    ME = MELiveData.value,
                    Q = QLiveData.value,
                    DM = DMLiveData.value,
                    CO = COLiveData.value,
                    SU = SULiveData.value,
                    CM = CMLiveData.value,
                    R = RLiveData.value,
                    WL = WLLiveData.value,
                    OTH = OTHLiveData.value
                )
            ).collect {
                when (it) {
                    is ResultWrapper.Failure -> {
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(
                            ErrorMessage(title = "Error", message = it.e.localizedMessage)
                        )
                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {
                        loadingLiveData.postValue(false)
                        message.postValue(it.data)
                    }
                }
            }

        }
    }

    fun exportData() {
        viewModelScope.launch (dispatcher){
            oeeUseCase.getFile().collect{
                when(it){
                    is ResultWrapper.Failure -> {
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(ErrorMessage(
                            title = "Error",
                            message = it.e.localizedMessage
                        ))
                    }
                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }
                    is ResultWrapper.Success -> {
                        loadingLiveData.postValue(false)
                        notificationHelper.showDownloadNotification(it.data!!)
                    }
                }
            }
        }
    }
}