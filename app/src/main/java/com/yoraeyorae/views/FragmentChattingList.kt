package com.yoraeyorae.views

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
import com.yoraeyorae.adapters.recycler.RecyclerChattingListAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerChattingListData
import com.yoraeyorae.databinding.FragmentChattingListBinding
import com.yoraeyorae.viewmodel.FragmentChattingListViewModel

class FragmentChattingList : Fragment() {

    private lateinit var bind : FragmentChattingListBinding
    private lateinit var navController: NavController
    private val viewModel : FragmentChattingListViewModel by viewModels()

    private lateinit var listAdapter: RecyclerChattingListAdapter
    private var listData = mutableListOf<RecyclerChattingListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_chatting_list, container, false)
        bind.view = this
        bind.viewModel = viewModel
        bind.lifecycleOwner = this

        (activity as ActivityMain).setNavigationVisibility(true)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        if (requireContext().applicationContext != null) {
            listAdapter = RecyclerChattingListAdapter(navController)
            bind.rvChattingList.adapter = listAdapter
            bind.rvChattingList.setHasFixedSize(true)
        }

        setTestData()
    }

    fun setTestData() {
        listData.apply{
            add(RecyclerChattingListData(partner="테스트1", lastChat="임시데이터입니다."))
            add(RecyclerChattingListData(partner="테스트2", lastChat="임시데이터입니다."))
            add(RecyclerChattingListData(partner="테스트3", lastChat="임시데이터입니다."))
            add(RecyclerChattingListData(partner="테스트4", lastChat="임시데이터입니다."))
            add(RecyclerChattingListData(partner="테스트5", lastChat="임시데이터입니다."))
            add(RecyclerChattingListData(partner="테스트6", lastChat="임시데이터입니다."))
        }
        listAdapter.addAllItem(listData)
    }

}