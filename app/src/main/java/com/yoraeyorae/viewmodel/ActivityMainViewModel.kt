package com.yoraeyorae.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityMainViewModel: ViewModel() {

    private val _curMode = MutableLiveData<Boolean>(true)
    val curMode : LiveData<Boolean> = _curMode

    private val _isMissionAccept = MutableLiveData<Boolean>(false)
    val isMissionAccept : LiveData<Boolean> = _isMissionAccept

    private val _missionCategory = MutableLiveData<String>()
    private val _missionDestination = MutableLiveData<String>()
    private val _missionStep = MutableLiveData<String>()

    val missionCategory : LiveData<String> = _missionCategory
    val missionDestination : LiveData<String> = _missionDestination
    val missionStep : LiveData<String> = _missionStep

    private val _alertDialogResult = MutableLiveData<Boolean>()
    val alertDialogResult : LiveData<Boolean> = _alertDialogResult

    private val _userName = MutableLiveData<String>()
    private val _userPhone = MutableLiveData<String>()
    private val _userTotalPoint = MutableLiveData<String>()
    val userName : LiveData<String> = _userName
    val userPhone : LiveData<String> = _userPhone
    val userTotalPoint : LiveData<String> = _userTotalPoint

    private val _isShowDetail = MutableLiveData<Boolean>(false)
    val isShowDetail : LiveData<Boolean> = _isShowDetail

    init {
        setUserData()
    }

    fun setUserData() {
        _userName.value = "똥쟁이"
        _userPhone.value = "010-1234-5678"
        _userTotalPoint.value = "₩123,456,789"
    }

    fun setUserMode(mode : Boolean) { //
        _curMode.value = mode
    }

    fun setMissionAccept(isAccept : Boolean) {
        _isMissionAccept.value = isAccept
    }

    fun setMissionData( category : String, destination : String, step : String) {
        _missionCategory.value = category
        _missionDestination.value = destination
        _missionStep.value = step
    }

    fun setMissionClear() {
        _missionCategory.value = ""
        _missionDestination.value = ""
        _missionStep.value = ""
        _isMissionAccept.value = false
    }

    fun AlertDialogResult(data: Boolean){
        _alertDialogResult.value = data
    }

    fun onClickMyMoney() {
        _isShowDetail.value = isShowDetail.value != true
    }
}