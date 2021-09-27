package com.yoraeyorae.viewmodel.step

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MissionInfoViewModel : ViewModel() {

    private val _isMissionAccept = MutableLiveData<Boolean>()
//    private val _category = MutableLiveData<String>()
//    private val _destination = MutableLiveData<String>("GS25 - 성수클래스점")
//    private val _step = MutableLiveData<String>("구매완료")
//    private val _img = MutableLiveData<String>(
//        Uri.parse("Resource://" + R::class.java.getPackage().name + "/" + R.drawable.sample_convenience)
//            .toString()
//    )
    private val _timer = MutableLiveData<String>("30:00")
    private val _stepCount = MutableLiveData<Int>(0)

    private val _test = MutableLiveData<Boolean>()
    val test: LiveData<Boolean> = _test

    val isMissionAccept : LiveData<Boolean> = _isMissionAccept
//    val category: LiveData<String> = _category
//    val destination: LiveData<String> = _destination
//    val step: LiveData<String> = _step
//    val img: LiveData<String> = _img
    val timer: LiveData<String> = _timer
    val stepCount: LiveData<Int> = _stepCount

    init {
//        setMissionInfo()
    }

//    fun missionClear() {
//        _category.value = ""
//        _destination.value = ""
//        _step.value = ""
//        _stepCount.value = 0
//    }
//
//    fun setMissionInfo(cate: String, dest: String, step: String) {
//        _category.value = cate
//        _destination.value = dest
//        _step.value = step
//    }
//
//    fun setCameraImage(view: ImageView, url: String) {
//        bindImageFromUrl(view, url)
//    }
//
//    fun setStepBtnText(str: String) {
//        _step.value = str
//    }
//
//    fun onClickBtnStep() {
//        when (stepCount.value) {
//            0 -> {
//                when (category.value.toString()) {
//                    "편의점" -> {
////                        _stepCount.value?.let { if (it == 0) _stepCount.value = it + 1 }
//                        _stepCount.value = _stepCount.value?.plus(1)
//                    }
//                    "종량제" -> {
//
//                    }
//
//                }
//            }
//            1 -> {
//                when(category.value.toString()) {
//                    "편의점" -> {
//                        _stepCount.value?.let { if( it == 1 ) _stepCount.value = it + 1 }
//                    }
//                    "종량제" -> {
//
//                    }
//                }
//
//            }
//        }
//
//    }


}