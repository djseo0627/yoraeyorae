package com.yoraeyorae.views

sealed class DialogResult {
    object No : DialogResult()
    class Yes(val value: String? = null) : DialogResult()
}

interface DialogResultListener {
    fun onDialogResult(result : DialogResult)
}