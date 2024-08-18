package com.route.legrand.fragments.production.oee

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
    var dateLiveData = MutableLiveData<String>(null)
    var timeLiveData = MutableLiveData<String>(null)
    var shiftLiveData = MutableLiveData<String>(null)
    var machineLiveData = MutableLiveData<String>(null)
    var operatorLiveData = MutableLiveData<String>(null)
    var partNumberLiveData = MutableLiveData<String>(null)
    var cavityNumberLiveData = MutableLiveData<String>(null)
    var totalProducedLiveData = MutableLiveData<String>(null)
    var workCvLiveData = MutableLiveData<String>(null)
    var CTLiveData = MutableLiveData<String>(null)
    var ELiveData = MutableLiveData<String>(null)
    var MOLiveData = MutableLiveData<String>(null)
    var HLiveData = MutableLiveData<String>(null)
    var MELiveData = MutableLiveData<String>(null)
    var QLiveData = MutableLiveData<String>(null)
    var DMLiveData = MutableLiveData<String>(null)
    var COLiveData = MutableLiveData<String>(null)
    var SULiveData = MutableLiveData<String>(null)
    var CMLiveData = MutableLiveData<String>(null)
    var RLiveData = MutableLiveData<String>(null)
    var WLLiveData = MutableLiveData<String>(null)
    var OTHLiveData = MutableLiveData<String>(null)
    var listOfPartNUmber = MutableLiveData<List<String>>()
    var messageAdded = MutableLiveData<String>()
    var messageEdited = MutableLiveData<String>()
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
                        messageAdded.postValue(it.data)
                    }
                }
            }

        }
    }

    fun exportData() {
        viewModelScope.launch(dispatcher) {
            oeeUseCase.getFile().collect {
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
                        notificationHelper.showDownloadNotification(it.data!!)
                    }
                }
            }
        }
    }

    fun editData() {
        viewModelScope.launch(dispatcher) {
            oeeUseCase.editData(
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
                        messageEdited.postValue(it.data)
                    }
                }
            }
        }
    }
}