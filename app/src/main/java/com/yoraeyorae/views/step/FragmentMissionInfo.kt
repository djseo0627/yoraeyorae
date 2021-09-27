package com.yoraeyorae.views.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMissionInfoBinding
import com.yoraeyorae.viewmodel.ActivityMainViewModel
import com.yoraeyorae.viewmodel.step.MissionInfoViewModel
import com.yoraeyorae.views.DialogFragmentAlert

class FragmentMissionInfo : Fragment()/*, DialogResultListener*/ {

    private lateinit var bind : FragmentMissionInfoBinding
    private val viewModel: MissionInfoViewModel by viewModels()
    private val activityMainViewModel : ActivityMainViewModel by activityViewModels()
    private var gbn : String = ""

    private fun showAlert(str : String) {
        val dialog = DialogFragmentAlert.dialogFragmentAlert(
            title = "테스트",
            message = "테스트입니다"
        )
        dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
            override fun onClickConfirm(view: View) {
                when(gbn) {
                    "구매완료" -> { setViewDeliveryStep() }
                    "미션수행완료" -> { setViewMissionNone() }
                }
            }

            override fun onClickCancel(view: View) { }
        })
        dialog.show(childFragmentManager, "MissionInfoFragment")
    }

    private fun setViewMissionNone() {
//        bind.containerMissionOn.visibility = View.GONE
//        bind.containerMissionNone.visibility = View.VISIBLE

//        bind.btnPurchaseComplete.visibility = View.VISIBLE
//        bind.btnMissionDetail.visibility = View.VISIBLE
//        bind.btnManyPeople.visibility = View.VISIBLE
//        bind.btnMissionAccomplished.visibility = View.GONE
    }

    private fun setViewDeliveryStep() {
//        bind.btnPurchaseComplete.visibility = View.GONE
//        bind.btnMissionDetail.visibility = View.INVISIBLE
//        bind.btnManyPeople.visibility = View.INVISIBLE
//        bind.btnMissionAccomplished.visibility = View.VISIBLE
    }

//    fun onClickMissionAccomplished(view: View ) {
//        gbn = bind.btnMissionAccomplished.text.toString()
//        showAlert("배달내용을 확인할 수 있게\n사진을 찍어주세요!")
//    }
//
//    fun onClickPurchaseComplete(view: View) {
//        gbn = bind.btnPurchaseComplete.text.toString()
//        showAlert("구매목록과 영수증을\n다시 한 번 확인하세요!")
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_mission_info, container, false)
        bind.viewModel = viewModel
        bind.lifecycleOwner = this
        bind.view = this

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setAlertObserver()
//        setMissionStepObserver()
        setAcceptObserver()

//        bind.containerMissionNone.visibility = View.VISIBLE
//        bind.containerMissionOn.visibility = View.GONE

    }

    private fun setAcceptObserver() {
//        mainViewModel.missionAccept.observe(viewLifecycleOwner) {
//            if( it == true ) {
//                viewModel.setMissionInfo(mainViewModel.missionCategory.value.toString(),
//                    mainViewModel.missionDestination.value.toString(),
//                    mainViewModel.missionStep.value.toString())
//
//                bind.containerMissionNone.visibility = View.GONE
//                bind.containerMissionOn.visibility = View.VISIBLE
//
//            }
//        }
    }

//    private fun setMissionStepObserver() {
//        mainViewModel.alertDialogResult.observe(viewLifecycleOwner) {
//            if(viewModel.stepCount.value == 1) {
//                bind.btnMissionDetail.visibility = View.INVISIBLE
//                bind.btnManyPeople.visibility = View.INVISIBLE
//                viewModel.setStepBtnText("미션수행완료")
//            } else if( viewModel.stepCount.value == 2) {
//                //미션완료
//                mainViewModel.setMissionClear()
//                viewModel.missionClear()
////                bind.containerMissionNone.visibility = View.VISIBLE
//                bind.containerMissionOn.visibility = View.GONE
//                bind.btnMissionDetail.visibility = View.VISIBLE
//                bind.btnManyPeople.visibility = View.VISIBLE
//            }
//        }
//    }



}

