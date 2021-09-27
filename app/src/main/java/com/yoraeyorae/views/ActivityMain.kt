package com.yoraeyorae.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yoraeyorae.R
import com.yoraeyorae.databinding.ActivityMainBinding
import com.yoraeyorae.viewmodel.ActivityMainViewModel
import kotlin.system.exitProcess

class ActivityMain :
    ActivityViewBase(TransitionMode.HORIZON), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var bind : ActivityMainBinding
    private lateinit var navController : NavController

    private val viewModel : ActivityMainViewModel by viewModels()

    //SharedPreferences 인자
    private lateinit var prefs  : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
//    private var bCurMode : Boolean = true //true : 등록자모드(default), false : 수행자모드

//    override fun onDialogResult(result: DialogResult) {
//        when(result) {
//            is DialogResult.No -> Toast.makeText(this, "취소!!!", Toast.LENGTH_SHORT).show()
//            is DialogResult.Yes -> Toast.makeText(this, "확인!!!${result.value}", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun getCurrentMode(): Boolean {
        return viewModel.curMode.value!!
    }

    fun setNavigationVisibility(set : Boolean) {
        if(set)
            bind.navigation.visibility = View.VISIBLE
        else
            bind.navigation.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        bind.viewModel = viewModel
        bind.lifecycleOwner = this
        init()

        viewModel.curMode.observe(this) {
            setUserMode(it)

        }
        bind.navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setUserMode(curMode : Boolean) {
        editor.putBoolean("currentMode", curMode)
        editor.commit()
        navController.popBackStack()
        if(curMode) {
            //등록자모드(false)면 수행자모드로 세팅
            navController.navigate(R.id.fragmentRegisterMain)
            bind.navigation.menu[2].title = "수행자전환"
            bind.navigation.menu[3].title = "등록미션함"
        }
        else {
            //수행자모드면, 등록자모드 변경으로 세팅
            navController.navigate(R.id.fragmentPerformerMain)
            bind.navigation.menu[2].title = "등록자전환"
            bind.navigation.menu[3].title = "선점미션함"
        }
    }

    private fun fnGoHome() {
        var fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.nav_main_container)
        if(fragment != null) {
            if (viewModel.curMode.value == true) {   //true : 등록자. 등록자 홈으로
                if (fragment.childFragmentManager.fragments[0] !is FragmentMainRegister) {
                    navController.popBackStack()
                    navController.navigate(R.id.fragmentRegisterMain)
                }
            } else {  //false : 수행자. 수행자 홈으로
                if (fragment.childFragmentManager.fragments[0] !is FragmentMainPerformer) {
                    navController.popBackStack()
                    navController.navigate(R.id.fragmentPerformerMain)
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bottom_home -> {
                navController.popBackStack()
                fnGoHome()
            }
            R.id.bottom_chat -> {
                navController.popBackStack()
                navController.navigate(R.id.fragmentChatting)
            }
            R.id.bottom_change -> {
                navController.popBackStack()
                if( viewModel.curMode.value == true )
                    viewModel.setUserMode(false)
                else
                    viewModel.setUserMode(true)
            }
            R.id.bottom_mission_list -> {
                navController.popBackStack()
                navController.navigate(R.id.fragmentMissionList)
            }
            R.id.bottom_my_page -> {
                navController.popBackStack()

            }
        }
        return true
    }

    private fun init() {
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        editor = prefs.edit()
//        bCurMode = prefs.getBoolean("currentMode", true)

        //최초 메인화면 등록자
        navController = findNavController(R.id.nav_main_container)
        bind.navigation.setupWithNavController(navController)

        if( !prefs.getBoolean("currentMode", true) ) {
            viewModel.setUserMode(false)
        }
    }

    override fun onDestroy() {
        editor.putBoolean("currentMode", viewModel.curMode.value!!)
        editor.commit()

        super.onDestroy()
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_main_container)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

        if( backStackEntryCount == 0 ) {
            var currentTime = System.currentTimeMillis()
            var intervalTime = currentTime - pressTime

            if( intervalTime < 2000 ) {
                finishAffinity()
                System.runFinalization()
                exitProcess(0)
            } else {
                pressTime = currentTime
                Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
        else
            super.onBackPressed()
    }

}