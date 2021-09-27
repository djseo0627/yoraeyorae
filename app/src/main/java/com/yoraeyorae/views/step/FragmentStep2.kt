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
import com.yoraeyorae.databinding.FragmentStep2Binding
import com.yoraeyorae.viewmodel.MainPerformerViewModel
import com.yoraeyorae.viewmodel.step.FragmentStep2ViewModel
import com.yoraeyorae.views.DialogFragmentAlert

class FragmentStep2 : Fragment() {

    private lateinit var bind : FragmentStep2Binding
    private val viewModel: FragmentStep2ViewModel by viewModels()
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

    fun onClickPurchaseDone(view: View) {
        if(imageCaptured) {
            val dialog = DialogFragmentAlert.dialogFragmentAlert(
                title = "Confirm Alert",
                message = "구매목록과 영수증을\n다시 한 번 확인하세요!"
            )
            dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
                override fun onClickConfirm(view2: View) {
                    Navigation.findNavController(view)
                        .navigate(R.id.action_fragmentStep2_to_fragmentStep3)
                    onDetach()
                }

                override fun onClickCancel(view2: View) {}
            })
            dialog.show(childFragmentManager, "MissionInfoFragment")
        }
        else {
            Toast.makeText(requireContext().applicationContext, "구매목록과 영수증 사진을 촬영해주세요!!!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_step2, container, false)
        bind.lifecycleOwner = this
        bind.view = this
        bind.viewModel = viewModel
        bind.missionInfo = missionInfo

        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}