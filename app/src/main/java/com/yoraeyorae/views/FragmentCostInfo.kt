package com.yoraeyorae.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentCostInfoBinding

class FragmentCostInfo : Fragment() {

    private lateinit var bind : FragmentCostInfoBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        bind.btnMissionRegist.setOnClickListener {

            navController.navigate(R.id.action_fragmentCostInfo_to_fragmentWaiting)
        }
    }

    fun checkRemainMoney(): Boolean {

        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCostInfoBinding.inflate(inflater, container, false)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCostInfo().apply {
                arguments = Bundle().apply {
                }
            }
    }
}