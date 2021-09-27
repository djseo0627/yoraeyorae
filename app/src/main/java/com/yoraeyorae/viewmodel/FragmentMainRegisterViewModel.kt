package com.yoraeyorae.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoraeyorae.adapters.BindingAdapters.bindImageFromUrl

class FragmentMainRegisterViewModel : ViewModel() {

    private val _imgUrl = MutableLiveData<String>()

    val imgUrl : LiveData<String> = _imgUrl


    fun setImage(view : ImageView, url : String?) {
        bindImageFromUrl(view, url)
    }
}