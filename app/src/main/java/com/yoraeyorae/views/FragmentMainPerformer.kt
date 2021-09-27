package com.yoraeyorae.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMainPerformerBinding
import com.yoraeyorae.viewmodel.MainPerformerViewModel
import com.yoraeyorae.viewmodel.ActivityMainViewModel
import com.yoraeyorae.views.ActivityMain

class FragmentMainPerformer : Fragment() {
    private lateinit var bind : FragmentMainPerformerBinding
    private val viewModel : MainPerformerViewModel by viewModels()
    private val activityMainViewModel : ActivityMainViewModel by activityViewModels()

    private lateinit var navController : NavController

    fun onClickCharge(view : View) {
        //navController.navigate(R.id.pointChargeFragment)
        Navigation.findNavController(view).navigate(R.id.action_performerMainFragment_to_pointChargeFragment)
    }

    fun onClickMissionMap(view : View) {
        bind.containerMapView.visibility = View.VISIBLE

        val fragment = when (view) {
            bind.ivNearMission -> FragmentMissionMap.newInstance("NearMission")
            bind.ivRegionMission -> FragmentMissionMap.newInstance("RegionMission")
            else -> return
        }
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.vertical_enter, R.anim.horizon_exit)
            .replace(R.id.container_map_view, fragment)
            .commit()


//        navController.popBackStack()
//        navController.navigateUp()
//        navController.navigate(R.id.fragmentMissionMap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_main_performer, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        bind.mainViewModel = activityMainViewModel
        bind.view = this

        bind.layoutMainCommon.ivMainAlarm.setOnClickListener{
            val intent = Intent(activity, ActivityIDCard::class.java)
            startActivity(intent)
            (activity as ActivityMain).finish()
        }


        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_mission_info)
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

        bind.layoutMainCommon.toggleMainAutoCall.visibility = View.VISIBLE

    }

    fun showMissionDialog(view:View) {
        val dialog = DialogFragmentMissionInfo()
        dialog.setMissionClickListener(object : DialogFragmentMissionInfo.MissionClickListener{
            override fun onMissionAccept() {
                Toast.makeText(requireContext().applicationContext, "수락", Toast.LENGTH_SHORT).show()
//                mainViewModel.setMissionData("편의점", "GS25-성수클래스점", "구매완료")
//                mainViewModel.setMissionAccept(true)
                navController.popBackStack()
                navController.navigate(R.id.fragmentStep1)
            }

            override fun onMissionReject() {
                Toast.makeText(requireContext().applicationContext, "거절", Toast.LENGTH_SHORT).show()
            }
        })
        dialog.show(
            childFragmentManager, "MainPerformerFragment"
        )
    }

}