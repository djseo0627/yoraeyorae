package com.yoraeyorae.views.step

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentStepNoneBinding
import com.yoraeyorae.viewmodel.ActivityMainViewModel
import com.yoraeyorae.viewmodel.step.FragmentStepNoneViewModel

class FragmentStepNone : Fragment() {

    private val viewModel: FragmentStepNoneViewModel by viewModels()
    private val activityMainViewModel : ActivityMainViewModel by activityViewModels()
    private lateinit var bind : FragmentStepNoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_step_none, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityMainViewModel.isMissionAccept.observe(viewLifecycleOwner) {
            if( it == true ) {

            }
        }


    }
}