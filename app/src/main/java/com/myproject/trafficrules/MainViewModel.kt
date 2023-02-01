package com.myproject.trafficrules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainViewModel : ViewModel() {

    private var countToExit = 0
    private var correctAnswers = 0

    private val _correctAnswersAmount = MutableStateFlow(0)
    val correctAnswersAmount = _correctAnswersAmount.asStateFlow()

    private val _buttonStateLiveData = MutableLiveData<Boolean>()
    val buttonStateLiveData : LiveData<Boolean> = _buttonStateLiveData

    fun enableButton() {
        _buttonStateLiveData.value = true
    }

    fun disableButton() {
        _buttonStateLiveData.value = false
    }

    fun increaseCorrectAnswers() {
        correctAnswers++
        _correctAnswersAmount.value = correctAnswers
    }

    fun exit() {
        if (countToExit == 2) exitProcess(0)
        }

    fun addCountToExit() {
        viewModelScope.launch {
            countToExit++
            delay(1400L)
            countToExit = 0
        }
    }
}
