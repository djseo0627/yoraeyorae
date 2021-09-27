package com.yoraeyorae.views

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.PagerSnapHelper
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.RecyclerAccountAdapter
import com.yoraeyorae.adapters.recycler.RecyclerChattingAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerAccountData
import com.yoraeyorae.databinding.FragmentChargeBinding
import com.yoraeyorae.viewmodel.FragmentChargeViewModel

class FragmentCharge : Fragment() {

    private lateinit var bind : FragmentChargeBinding
    private val viewModel : FragmentChargeViewModel by viewModels()

    private var accountData = mutableListOf<RecyclerAccountData>()
    private lateinit var adapter : RecyclerAccountAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

//    fun onClickAddButtons(view : View) {
//        when(view) {
//            bind.btnChargePlus2000 -> {
//                viewModel.addAmount(2000)
////                viewModel.setAmount(20000)
//            }
//            bind.btnChargePlus5000 -> {
//                viewModel.addAmount(5000)
//            }
//            bind.btnChargePlus10000 -> {
//                viewModel.addAmount(10000)
//            }
//            bind.btnChargePlus50000 -> {
//                viewModel.addAmount(50000)
//            }
//            bind.btnChargePlus100000 -> {
//                viewModel.addAmount(100000)
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_charge, container, false)
        bind.view = this
        bind.viewModel = viewModel
        bind.lifecycleOwner = this


        if (requireContext().applicationContext != null) {
            adapter = RecyclerAccountAdapter()
            bind.rvAccount.adapter = adapter
            bind.rvAccount.setHasFixedSize(true)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(bind.rvAccount)
        }

        val bitmap = BitmapFactory.decodeResource((activity as ActivityMain).resources, R.drawable.kakao_bank_account)
        adapter.addItem(RecyclerAccountData(accountNum = "333******2222", accountName = "계좌번호", bank = "카카오뱅크", img = bitmap))
        adapter.addItem(RecyclerAccountData(accountNum = "111******2222", accountName = "계좌번호", bank = "카카오뱅크", img = bitmap))
        adapter.addItem(RecyclerAccountData(accountNum = "222******2222", accountName = "계좌번호", bank = "카카오뱅크", img = bitmap))
        adapter.addItem(RecyclerAccountData(accountNum = "444******2222", accountName = "계좌번호", bank = "카카오뱅크", img = bitmap))

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    fun onClickCharge(view : View) {
        navController.navigate(R.id.action_fragmentPointCharge_to_fragmentChargePassword)
    }
}
