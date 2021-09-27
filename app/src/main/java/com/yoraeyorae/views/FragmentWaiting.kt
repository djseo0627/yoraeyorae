package com.yoraeyorae.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentWaitingBinding
import com.yoraeyorae.viewmodel.FragmentWaitingViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class FragmentWaiting : Fragment() {

    private val viewModel: FragmentWaitingViewModel by viewModels()
    private lateinit var bind: FragmentWaitingBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_waiting, container, false)
        bind.viewModel = viewModel
        bind.view = this
        bind.lifecycleOwner = this

        Glide.with(this).load(R.raw.test).into(bind.ivWaiting)

        (activity as ActivityMain).setNavigationVisibility(false)

        return bind.root
//        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_mission_info)
//        if (navHostFragment != null) {
        navController = Navigation.findNavController(view)
//        }
    }

    fun onClickCorrection(view: View) {
        val dialog = DialogFragmentAlert.dialogFragmentAlert(
            title = "Confirm Alert",
            message = "미션을 수정하시겠습니까?"
        )
        dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
            override fun onClickConfirm(view2: View) {
                navController.navigate(R.id.action_fragmentWaiting_to_fragmentConvenience)
                (activity as ActivityMain).setNavigationVisibility(true)
//                navController.popBackStack()
                onDetach()
            }

            override fun onClickCancel(view2: View) {
                Toast.makeText(requireContext().applicationContext, "취소", Toast.LENGTH_SHORT).show()
                onDetach()
            }
        })
        dialog.show(childFragmentManager, "MissionInfoFragment")
    }

    fun onClickNext(view : View) {
        navController.navigate(R.id.action_fragmentWaiting_to_fragmentChattingRoom)
        (activity as ActivityMain).setNavigationVisibility(true)
    }

    fun onClickCancel(view: View) {
        val dialog = DialogFragmentAlert.dialogFragmentAlert(
            title = "Confirm Alert",
            message = "미션을 취소하시겠습니까?"
        )
        dialog.setAlertClickListener(object : DialogFragmentAlert.AlertClickListener {
            override fun onClickConfirm(view2: View) {
//                navController.popBackStack()
                navController.navigate(R.id.action_fragmentWaiting_to_fragmentMissionCancel)
                (activity as ActivityMain).setNavigationVisibility(true)
                onDetach()
            }

            override fun onClickCancel(view2: View) {
                Toast.makeText(requireContext().applicationContext, "취소", Toast.LENGTH_SHORT).show()
                onDetach()
            }
        })
        dialog.show(childFragmentManager, "MissionInfoFragment")
    }


}