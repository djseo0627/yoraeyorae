package com.yoraeyorae.views

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.DialogFragmentMissionMapBinding
import com.yoraeyorae.viewmodel.DialogFragmentMissionMapViewModel
import com.yoraeyorae.views.ActivityMain
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class DialogFragmentMissionMap : DialogFragment() {

    private lateinit var bind : DialogFragmentMissionMapBinding
    private val viewModel : DialogFragmentMissionMapViewModel by viewModels()
    private lateinit var missionClickListener: MissionClickListener

    companion object {
        fun newInstance() = DialogFragmentMissionMap()
    }

    interface MissionClickListener {
        fun onCloseMap()
    }

    fun setMissionClickListener(missionClickListener : MissionClickListener) {
        this.missionClickListener = missionClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    fun onClickClose(view : View) {
        missionClickListener.onCloseMap()
        dismiss()
    }
    private fun setMissionPoint() {
        val mapView = MapView(activity as ActivityMain)
        bind.kakaomapMissionPoint.addView(mapView)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.544433, 127.056729), true)
        mapView.setCalloutBalloonAdapter(MissionBalloonAdapter(layoutInflater))
        //마커 추가
        val missionPoint1 = MapPOIItem().apply {
            itemName = "미션1"
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
        mapView.addPOIItem(missionPoint1)

        val missionPoint2 = MapPOIItem().apply {
            itemName = "미션2"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5446495068023, 127.061392779847)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin  //클릭시마커모양
        }
        mapView.addPOIItem(missionPoint2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_mission_map, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        bind.view = this

        setMissionPoint()

        return bind.root
    }

    class MissionBalloonAdapter(inflater: LayoutInflater) : CalloutBalloonAdapter {
        val mCalloutBalloon: View = inflater.inflate(R.layout.balloon_point, null)
        val name: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_name)
        val address: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_address)

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
}