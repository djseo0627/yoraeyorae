package com.yoraeyorae.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class DialogFragmentMissionInfoViewModel : ViewModel() {
    private var missionCategory = "편의점"
    private var missionStart = "GS25 성수클래스점"
    private var missionDestination = "서울시 성동구 성수동1가 롯데캐슬"
    private var missionCost = "22,020"
    private var missionPay = "5,528"
    private var missionTimeLimit = "40:00"

    var category = ObservableField<String>()
    var start = ObservableField<String>()
    var destination = ObservableField<String>()
    var cost = ObservableField<String>()
    var pay = ObservableField<String>()
    var timelimit = ObservableField<String>()

    init {
        setCategoryData()
    }

    fun setCategoryData() {
        category.set(missionCategory)
        start.set(missionStart)
        destination.set(missionDestination)
        cost.set(missionCost)
        pay.set(missionPay)
        timelimit.set(missionTimeLimit)
    }


}