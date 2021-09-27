package com.yoraeyorae.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMissionCancelBinding

class FragmentMissionCancel : Fragment() {

    private lateinit var navController : NavController
    private lateinit var bind : FragmentMissionCancelBinding

    fun onClickConfirm(view : View) {
//        navController.popBackStack()
        navController.navigate(R.id.action_fragmentMissionCancel_to_fragmentRegisterMain)
        (activity as ActivityMain).setNavigationVisibility(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_mission_cancel, container, false)
        bind.view = this
        bind.lifecycleOwner = this



        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        super.onViewCreated(view, savedInstanceState)
    }

}