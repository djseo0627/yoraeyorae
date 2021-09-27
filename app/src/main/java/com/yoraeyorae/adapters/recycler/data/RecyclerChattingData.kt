package com.yoraeyorae.adapters.recycler.data

import android.graphics.Bitmap

data class RecyclerChattingData(
    val user : String = "",
    val text : String = "",
    val time : String = "",
    val type : String = "text",
    val img : Bitmap? = null
)
