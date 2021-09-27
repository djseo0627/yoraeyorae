package com.yoraeyorae.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogFragmentAlertViewModel : ViewModel() {

    private val _alertText = MutableLiveData<String>()
//    private val _alertClick = MutableLiveData<Boolean>()

//    val alertClick : LiveData<Boolean> = _alertClick
    val alertText : LiveData<String> = _alertText

    fun setAlertText(str : String) {
        _alertText.value = str
    }

//    fun onClickConfirmCancel(value : Boolean) {
//        _alertClick.value = value
//    }



}