package com.sample.player.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.player.domain.model.base.ErrorEntity
import com.sample.player.presentation.util.SingleEvent

open class BaseViewModel : ViewModel() {
    val showProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<SingleEvent<String>>()

    fun showProgressbar() {
        showProgress.value = true
    }

    fun hideProgressbar() {
        showProgress.value = false
    }

    fun showErrorMessage(error: ErrorEntity) {
        hideProgressbar()
        errorMessage.value = SingleEvent(error.errorMessage)
    }
}