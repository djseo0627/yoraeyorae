package com.yoraeyorae.viewmodel

import android.net.Uri
import androidx.lifecycle.*
import com.yoraeyorae.R

class MainPerformerViewModel : ViewModel() {

    private val _category = MutableLiveData<String>()
    private val _destination = MutableLiveData<String>("GS25 - 성수클래스점")
    private val _step = MutableLiveData<String>("구매완료")
    private val _img = MutableLiveData<String>(
        Uri.parse("Resource://" + R::class.java.getPackage().name + "/" + R.drawable.sample_convenience)
            .toString()
    )

    val category: LiveData<String> = _category
    val destination: LiveData<String> = _destination
    val step: LiveData<String> = _step
    val img: LiveData<String> = _img

//    private val _test = MutableLiveData<Event<Int>>()
//    val test : LiveData<Event<Int>> = _test
//
//    fun onClickCharge(view : ImageView) {
//        var a = view
//    }

}

//하나의 이벤트당 한 번의 처리만 하기 위한 확장함수
inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

class Event<out T>(private val content : T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}