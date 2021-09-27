package com.yoraeyorae.views.step

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentStep1Binding
import com.yoraeyorae.viewmodel.MainPerformerViewModel
import com.yoraeyorae.viewmodel.step.FragmentStep1ViewModel
import com.yoraeyorae.views.DialogFragmentAlert

class FragmentStep1 : Fragment() {

    private val viewModel: FragmentStep1ViewModel by viewModels()
    private val missionInfo: MainPerformerViewModel by viewModels()
    private lateinit var bind: FragmentStep1Binding

    private var imageCaptured = false
    private val launcher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            if (it != null) {
                bind.ivCaptureImage.setImageBitmap(it)
                imageCaptured = true
            } else {
                Toast.makeText(requireContext().applicationContext, "사진촬영 실패!!", Toast.LENGTH_SHORT).show()
            }
        }

    //    private val launcher : ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if(it.resultCode == RESULT_OK && it.data != null ) {
//                var imgUri = it.data?.data
//                try {
//                    imgUri?.let{
//                        if(Build.VERSION.SDK_INT < 28) {
//                            val bitmap = MediaStore.Images.Media.getBitmap(
//                                requireContext().contentResolver, imgUri
//                            )
//                            bind.ivCaptureImage.setImageBitmap(bitmap)
//                        }
//                        else {
//                            val source = ImageDecoder.createSource(requireContext().contentResolver, imgUri)
//                            val bitmap = ImageDecoder.decodeBitmap(source)
//                            bind.ivCaptureImage.setImageBitmap(bitmap)
//                        }
//                    }
//                } catch (e : Exception) {
//                    e.printStackTrace()
//                }
//            } else if( it.resultCode == RESULT_CANCELED) {
//                Toast.makeText(requireContext().applicationContext, "사진 촬영 취소", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(requireContext().applicationContext, "사진 촬영 실패", Toast.LENGTH_SHORT).show()
//            }
//    }
    fun onClickCapture(view: View) {
//        val cameraIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(imageCaptured) {
            val dialog = DialogFragmentAlert.dialogFragmentAlert(
                title = "Confirm Alert",
                message = "이미 사진을 촬영하셨습니다.\n다시 촬영할까요???"
            )
            dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
                override fun onClickConfirm(view2: View) {
                    launcher.launch()
                    onDetach()
                }

                override fun onClickCancel(view2: View) {
                    onDetach()
                }
            })
            dialog.show(childFragmentManager, "MissionInfoFragment")
        } else {
            launcher.launch()
        }
    }

    fun onClickArrive(view: View) {
        if (imageCaptured) {
            val dialog = DialogFragmentAlert.dialogFragmentAlert(
                title = "테스트",
                message = "테스트입니다"
            )
            dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
                override fun onClickConfirm(view2: View) {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_fragmentStep1_to_fragmentStep2)
                    onDetach()
                }

                override fun onClickCancel(view2: View) {}
            })
            dialog.show(childFragmentManager, "MissionInfoFragment")
        } else {
            Toast.makeText(requireContext().applicationContext, "미션수행지에 도착한 사진을 촬영해주세요!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_step1, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        bind.view = this
        bind.missionInfo = missionInfo

        return bind.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }


}