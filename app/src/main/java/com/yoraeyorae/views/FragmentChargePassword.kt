package com.yoraeyorae.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentChargePasswordBinding
import com.yoraeyorae.viewmodel.FragmentChargePasswordViewModel

class FragmentChargePassword : Fragment() {

    private val viewModel: FragmentChargePasswordViewModel by viewModels()
    private lateinit var bind : FragmentChargePasswordBinding

    private lateinit var navController : NavController

    private var step = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_charge_password, container, false)
        bind.view = this
        bind.viewModel = viewModel
        bind.lifecycleOwner = this

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    fun onClickNumPad(view : View) {


    }

}