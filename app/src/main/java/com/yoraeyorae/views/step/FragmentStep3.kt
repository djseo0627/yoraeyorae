package com.yoraeyorae.views.step

import android.content.Intent
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
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentStep3Binding
import com.yoraeyorae.viewmodel.MainPerformerViewModel
import com.yoraeyorae.viewmodel.step.FragmentStep3ViewModel
import com.yoraeyorae.views.ActivityIDCard
import com.yoraeyorae.views.ActivityMain
import com.yoraeyorae.views.DialogFragmentAlert

class FragmentStep3 : Fragment() {

    private lateinit var bind : FragmentStep3Binding
    private val viewModel: FragmentStep3ViewModel by viewModels()
    private val missionInfo : MainPerformerViewModel by viewModels()

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

    fun onClickCapture(view : View) {
        if(imageCaptured) {
            val dialog = DialogFragmentAlert.dialogFragmentAlert(
                title = "Confirm Alert",
                message = "이미 사진을 촬영하셨습니다.\n다시 촬영할까요?"
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

    fun onClickMissionAccomplished(view : View) {
        if( imageCaptured ) {
            val dialog = DialogFragmentAlert.dialogFragmentAlert(
                title = "Confirm Alert",
                message = "배달 내용을 확인할 수 있게\n사진을 촬영해 주세요!"
            )
            dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
                override fun onClickConfirm(view2: View) {
//                    Navigation.findNavController(view)
//                        .navigate(R.id.action_fragmentStep3_to_fragmentStepNone)

                    val intent = Intent(activity, ActivityIDCard::class.java)
                    startActivity(intent)
                    (activity as ActivityMain).finish()

                    onDetach()
                }

                override fun onClickCancel(view2: View) {
                    onDetach()
                }
            })
            dialog.show(childFragmentManager, "MissionInfoFragment")
        }
        else {
            Toast.makeText(requireContext().applicationContext, "배달 상태를 알 수 있게 사진을 촬영해주세요!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_step3, container, false)
        bind.lifecycleOwner = this
        bind.view = this
        bind.viewModel = viewModel
        bind.missionInfo = missionInfo

        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}