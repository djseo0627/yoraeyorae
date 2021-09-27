package com.yoraeyorae.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMainRegisterBinding
import com.yoraeyorae.viewmodel.FragmentMainRegisterViewModel
import com.yoraeyorae.viewmodel.ActivityMainViewModel


class FragmentMainRegister : Fragment() {
    private lateinit var bind : FragmentMainRegisterBinding
    private val viewModelFragment : FragmentMainRegisterViewModel by viewModels()
    private val activityMainViewModel : ActivityMainViewModel by activityViewModels()

    private lateinit var navController: NavController
    private lateinit var aAnimation : TranslateAnimation

    private fun setMyMoneyAni(){
//        bind.layoutMainCommon.cvMainMyMoneyDetail.alpha = 0.0F
//        bind.layoutMainCommon.cvMainMyMoneyDetail.animate()
//            .translationY(bind.layoutMainCommon.cvMainMyMoneyDetail.height.toFloat())
//            .alpha(1.0F)
//            .setListener(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFragment.setImage(bind.ivTest, "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory&fname=https://k.kakaocdn.net/dn/EShJF/btquPLT192D/SRxSvXqcWjHRTju3kHcOQK/img.png")

        navController = Navigation.findNavController(view)

        setMyMoneyAni()
        bind.layoutMainCommon.toggleMainAutoCall.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_main_register,container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModelFragment
        bind.mainViewModel = activityMainViewModel
        bind.view = this

        return bind.root
    }

    fun onClickConvenience(view : View) {
        navController.navigate(R.id.action_registerMainFragment_to_convenienceFragment)
    }

    fun onClickCharge(view : View) {
        navController.navigate(R.id.action_registerMainFragment_to_pointChargeFragment)
    }



}