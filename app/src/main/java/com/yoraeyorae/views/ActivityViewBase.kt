package com.yoraeyorae.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.yoraeyorae.R

abstract class ActivityViewBase(
    private val transitionMode : TransitionMode = TransitionMode.NONE
): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        onInject(savedInstanceState)
        super.onCreate(savedInstanceState)

        when(transitionMode) {
            TransitionMode.HORIZON -> overridePendingTransition(R.anim.horizon_enter, R.anim.none)
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.vertical_enter, R.anim.none)
            else -> Unit
        }
    }

    override fun finish() {
        super.finish()
        when(transitionMode) {
            TransitionMode.HORIZON -> overridePendingTransition(R.anim.none, R.anim.horizon_enter)
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.none, R.anim.vertical_exit)
            else -> Unit
        }
    }
    var pressTime : Long = 0L

//    override fun onBackPressed() {
//        var currentTime = System.currentTimeMillis()
//        var intervalTime = currentTime - pressTime
//
//        if( intervalTime < 2000 ) {
//            super.onBackPressed()
//            finishAffinity()
//            System.runFinalization()
//            exitProcess(0)
//        } else {
//            pressTime = currentTime
//            Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
//        }
//
//        if(isFinishing) {
//            when(transitionMode) {
//                TransitionMode.HORIZON -> overridePendingTransition(R.anim.none, R.anim.horizon_exit)
//                TransitionMode.VERTICAL -> overridePendingTransition(R.anim.none, R.anim.vertical_exit)
//                else -> Unit
//            }
//        }
//    }

    fun showAlert1(strTitle : String, strMessage : String) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup, null)
        val textView : TextView = view.findViewById(R.id.tvAlert)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle(strTitle)
            .setPositiveButton("확인") { dialog, which ->
                Toast.makeText(applicationContext, "touched \"Save\"", Toast.LENGTH_SHORT).show()
            }
            .create()
//            .setNeutralButton("취소", null)

        textView.text = strMessage

        alertDialog.setCancelable(false)    //뒤로가기키/배경터치 방지
        alertDialog.setView(view)
        alertDialog.window?.setLayout(500, 400)  //크기조절
        alertDialog.show()
    }

//    fun showConfirmDeny(strTitle : String, strMessage : String) {
//        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view = inflater.inflate(R.layout.alert_popup, null)
//        val textView : TextView = view.findViewById(R.id.tvAlert)
//
//        val alertDialog = AlertDialog.Builder(this)
//            .setTitle(strTitle)
//            .setPositiveButton("확인") { dialog, which ->
//                Toast.makeText(applicationContext, "touched \"Save\"", Toast.LENGTH_SHORT).show()
//            }
//            .setPositiveButton("취소", null)
//            .create()
//
//        textView.text = strMessage
//
//        alertDialog.setCancelable(false)    //뒤로가기키/배경터치 방지
//        alertDialog.setView(view)
//        alertDialog.window?.setLayout(500, 400)  //크기조절
//        alertDialog.show()
//    }

    enum class TransitionMode{
        NONE,
        HORIZON,
        VERTICAL
    }
}