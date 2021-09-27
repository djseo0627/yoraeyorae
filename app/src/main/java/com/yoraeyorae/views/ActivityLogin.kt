package com.yoraeyorae.views

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.yoraeyorae.R
import com.yoraeyorae.databinding.ActivityLoginBinding
import com.yoraeyorae.viewmodel.ActivityLoginViewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ActivityLogin : ActivityViewBase(TransitionMode.HORIZON) {
    private lateinit var bind: ActivityLoginBinding
    private val viewModel : ActivityLoginViewModel by viewModels()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Yoraeyorae)
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        bind.view = this

        navController = findNavController(R.id.nav_login_fragment)

        getHashKey()
        getPermission()
    }

    private fun getPermission() {
        var permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {}
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                finish() //거절시 강제종료?
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage("허락해줘잉")
            .setDeniedMessage("아 에반데")
            .setPermissions(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check()
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }
        }
    }

//    private fun getHashKey(){
//        try {
//            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
//            val signatures = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                info.signingInfo.apkContentsSigners
//            } else {
//                TODO("VERSION.SDK_INT < P")
//            }
//            val md = MessageDigest.getInstance("SHA")
//            for (signature in signatures) {
//                val md: MessageDigest
//                md = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                val key = String(Base64.encode(md.digest(), 0))
//                Log.d("Hash key:", "!!!!!!!$key!!!!!!")
//            }
//        } catch (e: Exception) {
//            Log.e("name not found", e.toString())
//        }
//    }
}