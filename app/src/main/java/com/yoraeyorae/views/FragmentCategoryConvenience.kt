package com.yoraeyorae.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.kakaomap.KakaoApi
import com.yoraeyorae.kakaomap.resdata.AddressSearchResult
import com.yoraeyorae.kakaomap.resdata.CategorySearchResult
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.RecyclerConvenienceAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerConvenienceData
import com.yoraeyorae.adapters.recycler.RecyclerSelectedItemAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerSelectedItemData
import com.yoraeyorae.databinding.FragmentConvenienceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class FragmentCategoryConvenience : Fragment(), View.OnClickListener {

    private lateinit var bind: FragmentConvenienceBinding
    private lateinit var navController: NavController

//    private lateinit var convenienceAdapter: RecyclerConvenienceAdapter
//    private lateinit var selectedItemAdapter: RecyclerSelectedItemAdapter

    private var convenienceDatas = mutableListOf<RecyclerConvenienceData>()
    private var selectedItemDatas = mutableListOf<RecyclerSelectedItemData>()

//    private val viewModel = ConvenienceFragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

//        setConvenienceList()
//        setSelectedItemList()

        bind.btnConvenienceRegisterNext.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentConvenienceBinding.inflate(inflater, container, false)


//        if (requireContext().applicationContext != null) {
//            convenienceAdapter = ConvenienceRecyclerAdapter(requireContext().applicationContext)
//            bind.rvConvenience.adapter = convenienceAdapter
//
//            conveniData = viewModel.getConvenienceData(bind.tvMissionAddress.text.toString())
//
//            if (conveniData.size != 0) {
//                for (i in 0 until conveniData.size)
//                    datas.apply {
//                        add(
//                            ConvenienceRecyclerData(
//                                name = conveniData[i].place_name,
//                                distance = conveniData[i].distance
//                            )
//                        )
//                    }
//            }
//            convenienceAdapter.datas = datas
//            convenienceAdapter.notifyDataSetChanged()
//        }

        return bind.root
    }

//    private fun setSelectedItemList() {
//        if (requireContext().applicationContext != null) {
//            selectedItemAdapter = RecyclerSelectedItemAdapter(requireContext().applicationContext)
//            bind.rvSelectedItem.adapter = selectedItemAdapter
//            bind.rvSelectedItem.setHasFixedSize(true)
//
//            selectedItemDatas.apply {
//                add(RecyclerSelectedItemData(itemName="컵라면", itemCount = "1", itemImg = R.drawable.sample))
//                add(RecyclerSelectedItemData(itemName="콜라1+1", itemCount = "1", itemImg = R.drawable.sample))
//                add(RecyclerSelectedItemData(itemName="게토레이", itemCount = "1", itemImg = R.drawable.sample))
//                add(RecyclerSelectedItemData(itemName="생수", itemCount = "1", itemImg = R.drawable.sample))
//            }
//            selectedItemAdapter.addAllItems(selectedItemDatas)
//        }
//    }

//    private fun setConvenienceList() {
//        if (requireContext().applicationContext != null) {
//            convenienceAdapter = RecyclerConvenienceAdapter(requireContext().applicationContext)
//            bind.rvConvenience.setHasFixedSize(true)
//            bind.rvConvenience.adapter = convenienceAdapter
//
//            val api = KakaoApi.create()
//            val callAddress2Coord = api.getAddressSearchResult(bind.tvMissionAddress.text.toString())
//            callAddress2Coord.enqueue( object : Callback<AddressSearchResult>{
//                override fun onResponse(
//                    call: Call<AddressSearchResult>,
//                    response: Response<AddressSearchResult>
//                ) {
//                    response.body()?.let{ it1->
//                        val callConvenienceSearch = api.getCategorySearchResult(
//                            "CS2",
//                            it1.documents[0].x,
//                            it1.documents[0].y,
//                            "1000",
//                            "11",
//                            "15",
//                            "distance"
//                        )
//
//                        callConvenienceSearch.enqueue(object : Callback<CategorySearchResult> {
//                            override fun onResponse( call: Call<CategorySearchResult>, response: Response<CategorySearchResult> ) {
//                                if (response.isSuccessful) {
//                                    response.body()?.let { it2->
//                                        for(i in 0 until it2.documents.size)
//                                            convenienceDatas.apply {
//                                                add(RecyclerConvenienceData(name = it2.documents[i].place_name, distance = it2.documents[i].distance, img = R.drawable.sample))
//                                            }
//                                    }
////                                    convenienceAdapter.convenienceDatas = convenienceDatas
////                                    convenienceAdapter.notifyDataSetChanged()
//                                    convenienceAdapter.addAllConvenience(convenienceDatas)
//                                }
//                            }
//                            override fun onFailure(call: Call<CategorySearchResult>, t: Throwable) {
//                                Toast.makeText(requireContext().applicationContext, "서버와 통신에 실패하여 편의점 목록을 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
//                                return
//                            }
//                        })
//
//                    }
//                }
//
//                override fun onFailure(call: Call<AddressSearchResult>, t: Throwable) {
//                    Toast.makeText(requireContext().applicationContext, "서버와 통신에 실패하여 편의점 목록을 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
//                    Log.d("통신실패", "${t.message}")
//                    return
//                }
//
//            })
//        }
//    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btn_convenience_register_next -> {
                    navController.navigate(R.id.action_convenienceFragment_to_costInfoFragment)
                }
//                R.id.btn_convenience_charge -> {
//                    navController.navigate(R.id.action_convenienceFragment_to_pointChargeFragment)
//                }

            }
        }
    }


}