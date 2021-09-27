package com.yoraeyorae.adapters.recycler.data

import android.graphics.Bitmap

data class RecyclerAccountData(
    val accountNum : String = "",
    val accountName : String = "",
    val bank : String = "",
    val img : Bitmap? = null
)
