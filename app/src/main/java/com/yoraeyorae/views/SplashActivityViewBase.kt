package com.yoraeyorae.views

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import com.yoraeyorae.views.ActivityViewBase

class SplashActivityViewBase : ActivityViewBase(TransitionMode.HORIZON) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

        SystemClock.sleep(300)
        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()
//        overridePendingTransition(R.anim.horizon_enter, R.anim.horizon_exit)

    }
}