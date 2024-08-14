package com.route.legrand.activities.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.auth.UserLogin
import com.route.domain.usecases.auth.AuthUseCase
import com.route.legrand.base.BaseViewModel
import com.route.legrand.models.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {
    val loginEmailLiveData = MutableLiveData<String>("")
    val loginPasswordLiveDate = MutableLiveData<String>("")
    val loginEmailLiveDataError = MutableLiveData<String?>()
    val loginPasswordLiveDateError = MutableLiveData<String?>()
    var event = MutableLiveData<AuthEvents>(null)
    private val dispatcher = Dispatchers.IO


    fun login() {
        if (!validation()) return
        viewModelScope.launch(dispatcher) {
            authUseCase.execute(
                UserLogin(
                    email = loginEmailLiveData.value!!,
                    password = loginPasswordLiveDate.value!!
                )
            ).collect {
                when (it) {
                    is ResultWrapper.Failure -> {
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(
                            ErrorMessage("Error", message = it.e.localizedMessage)
                        )

                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {
                        loadingLiveData.postValue(false)
                        event.postValue(AuthEvents.navigateToMain)

                    }
                }
            }
        }
    }

    fun validation(): Boolean {
        var isValide = true
        if (loginEmailLiveData.value.isNullOrEmpty()) {

            loginEmailLiveDataError.value = "Please Enter Correct Email"
            isValide = false
        } else {
            loginEmailLiveDataError.value = null
        }
        if (loginPasswordLiveDate.value.isNullOrEmpty()) {

            loginPasswordLiveDateError.value = "Please Enter Correct Password"
            isValide = false
        } else {
            loginPasswordLiveDateError.value = null
        }
        return isValide
    }
}