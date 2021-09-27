package com.yoraeyorae.views

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yoraeyorae.R
import com.yoraeyorae.viewmodel.ActivityIDCardViewModel
import com.yoraeyorae.databinding.ActivityIdcardBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import java.util.*


class ActivityIDCard : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    private lateinit var bind : ActivityIdcardBinding
    private val viewModel : ActivityIDCardViewModel by viewModels()

    private val TAG : String = "opencv"
    private lateinit var matInput : Mat
    private lateinit var matResult : Mat

    private external fun ConvertRGBtoGray(matAddrInput: Long, matAddrResult: Long)

    companion object
    {
        init {
            System.loadLibrary("opencv_java4")
            System.loadLibrary("native-lib")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        bind = DataBindingUtil.setContentView<ActivityIdcardBinding>(this, R.layout.activity_idcard)
        bind.lifecycleOwner = this
        bind.view = this
        bind.viewModel = viewModel

        bind.openCvCameraView.visibility = SurfaceView.VISIBLE
        bind.openCvCameraView.setCvCameraViewListener(this)
        bind.openCvCameraView.setCameraIndex(0) //front-camera : 1 / back-camera : 0

    }

    override fun onPause() {
        super.onPause()
        if( bind.openCvCameraView != null ) bind.openCvCameraView.disableView()
    }

    override fun onResume() {
        super.onResume()
        if(!OpenCVLoader.initDebug()) {
            Log.d(TAG, "onResume::Internal OpenCV library not found.")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, mLoaderCallback)
        } else {
            Log.d(TAG, "onResume::OpenCV library found inside package.")
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if( bind.openCvCameraView != null ) bind.openCvCameraView.disableView()
    }

    private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                SUCCESS -> {
                    bind.openCvCameraView.enableView()
//                    mOpenCvCameraView.enableView()
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }

    override fun onCameraViewStarted(width: Int, height: Int) { }

    override fun onCameraViewStopped() { }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        matInput = inputFrame!!.rgba()

        if( matResult == null ) {
            matResult = Mat(matInput.rows(), matInput.cols(), matInput.type())
        }

        ConvertRGBtoGray(matInput.nativeObjAddr, matResult.nativeObjAddr)
        return matResult
    }

    protected fun getCameraViewList(): List<CameraBridgeViewBase?>? {
        return Collections.singletonList(bind.openCvCameraView)
    }


}