package com.yoraeyorae.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentChargeViewModel : ViewModel() {

    private val _amount = MutableLiveData<Int>(0)
    val amount: LiveData<Int> = _amount

    fun addAmount(add: Int) {
        _amount.value = amount.value?.plus(add)
    }


}