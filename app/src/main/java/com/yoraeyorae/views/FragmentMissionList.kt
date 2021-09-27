package com.yoraeyorae.views

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yoraeyorae.adapters.recycler.RecyclerMissionListAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerMissionListData
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMissionListBinding
import com.yoraeyorae.viewmodel.ActivityMainViewModel
import com.yoraeyorae.viewmodel.FragmentMissionListViewModel

class FragmentMissionList : Fragment() {

    private val viewModel: FragmentMissionListViewModel by viewModels()
    private val viewModelMain : ActivityMainViewModel by activityViewModels()
    private lateinit var bind : FragmentMissionListBinding

    private lateinit var missionListAdapter : RecyclerMissionListAdapter
    private var missionListData = mutableListOf<RecyclerMissionListData>()


    private fun setMissionList() {
        if (requireContext().applicationContext != null) {
            missionListAdapter = RecyclerMissionListAdapter(requireContext().applicationContext)
            bind.rvMissionList.adapter = missionListAdapter
//            bind.rvMissionList.setHasFixedSize(true)

            missionListData.apply {
                for(i in 0..20)
                {
                    add(
                        RecyclerMissionListData(
                            address = "서울시 성동구 성수동1가 롯데캐슬 102동",
                            distance = "300m",
                            total = "₩999,999",
                            fee = "₩11,111",
                            timeLimit = "40:00",
                            registTime = "2021.07.12 09:30:00",
                            thumnbnailUrl = Uri.parse("Resource://" + R::class.java.getPackage().name + "/" + R.drawable.sample_convenience)
                                .toString(),
                            curStateImgUrl = "",
                            categoryImgUrl = ""
                        )
                    )
                }
            }
            missionListAdapter.addAllItems(missionListData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_mission_list, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        bind.view = this

        setMissionList()

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if( viewModelMain.curMode.value == true ) {
            bind.tvTitle.text = "등록미션함"
            bind.tvTotalCount.text = "총 20건을 이용하셨습니다."
            bind.tvExpectedProfit.visibility = View.GONE
        }
        else {
            bind.tvTitle.text = "선점미션함"
            bind.tvTotalCount.text = "누적 99,999건 | 선점 3건"
            bind.tvExpectedProfit.visibility = View.VISIBLE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(FragmentMissionListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}