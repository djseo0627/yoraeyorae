package com.yoraeyorae.views

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.RecyclerPurchaseListAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerPurchaseListData
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import com.yoraeyorae.databinding.FragmentRegistConfirmBinding

class FragmentRegistConfirm : Fragment() {

    private lateinit var bind: FragmentRegistConfirmBinding

    private lateinit var purchaselistAdapter: RecyclerPurchaseListAdapter
    private var purchaselistDatas = mutableListOf<RecyclerPurchaseListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentRegistConfirmBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setPurchaseList()
        setMissionPoint()

    }

    private fun setMissionPoint() {
        val mapView = MapView(activity)
        bind.kakaomapMissionPoint.addView(mapView)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.544433, 127.056729), true)
        mapView.setCalloutBalloonAdapter(CustomBalloonAdapter(layoutInflater))
        //마커 추가
        val startPoint = MapPOIItem().apply {
            itemName = "미션시작지"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5452417439977, 127.056686462112)
            markerType = MapPOIItem.MarkerType.BluePin
            //커스텀 마커
//            markerType = MapPOIItem.MarkerType.BluePin              //마커모양
//            customImageResourceId = R.drawable.marker_blue          //커스텀마커이미지
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  //클릭시마커모양
//            customSelectedImageResourceId = R.drawable.marker_red   //클릭시마커이미지
//            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
//            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
        }
        mapView.addPOIItem(startPoint)

        val endPoint = MapPOIItem().apply {
            itemName = "미션완료지"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5446495068023, 127.061392779847)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin  //클릭시마커모양
            //커스텀 마커
//            markerType = MapPOIItem.MarkerType.BluePin              //마커모양
//            customImageResourceId = R.drawable.marker_blue          //커스텀마커이미지
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  //클릭시마커모양
//            customSelectedImageResourceId = R.drawable.marker_red   //클릭시마커이미지
//            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
//            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
        }
        mapView.addPOIItem(endPoint)
    }

    class CustomBalloonAdapter(inflater: LayoutInflater) : CalloutBalloonAdapter {
        private val mCalloutBalloon: View = inflater.inflate(R.layout.balloon_point, null)
        private val name: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_name)
        private val address: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_address)

        override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
            var geocoder: Geocoder

            // 마커 클릭 시 나오는 말풍선
            name.text = "주소표예정"   // 해당 마커의 정보 이용 가능
            address.text = getAddr(poiItem?.mapPoint)
            return mCalloutBalloon
        }

        override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
            // 말풍선 클릭 시
            address.text = "getPressedCalloutBalloon"
            return mCalloutBalloon
        }

        fun getAddr(point: MapPoint?): String {


            return ""
        }

    }

    private fun setPurchaseList() {
        purchaselistAdapter = RecyclerPurchaseListAdapter(requireContext().applicationContext)
        bind.rvPurchaseList.adapter = purchaselistAdapter

        purchaselistDatas.apply {
            add(
                RecyclerPurchaseListData(
                    purchaseName = "컵라면",
                    purchaseAmount = "1",
                    purchaseImg = R.drawable.sample
                )
            )
            add(
                RecyclerPurchaseListData(
                    purchaseName = "콜라",
                    purchaseAmount = "1",
                    purchaseImg = R.drawable.sample
                )
            )
            add(
                RecyclerPurchaseListData(
                    purchaseName = "소시지",
                    purchaseAmount = "1",
                    purchaseImg = R.drawable.sample
                )
            )
        }
        purchaselistAdapter.purchaselistDatas = purchaselistDatas
        purchaselistAdapter.notifyDataSetChanged()
    }
}
