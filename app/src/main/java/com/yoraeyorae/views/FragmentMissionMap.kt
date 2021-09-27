package com.yoraeyorae.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.location.Geocoder
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentMissionMapBinding
import com.yoraeyorae.viewmodel.FragmentMissionMapViewModel
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class FragmentMissionMap : Fragment() {

    private lateinit var bind: FragmentMissionMapBinding
    private val viewModel: FragmentMissionMapViewModel by viewModels()

    private val DEFAULT_TEXT_SIZE = 50f // xxxhdpi 기준
    private val XXXHDPI = 640f
    private val DBG = false

    companion object {
        const val KEY = "KEY"
        fun newInstance(data: String) = FragmentMissionMap().apply {
            arguments = Bundle().apply {
                putString(KEY, data)
            }
        }
    }

    val receiveData by lazy { requireArguments().getString(KEY) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_mission_map, container, false)
        bind.lifecycleOwner = this
        bind.view = this
        bind.viewModel = viewModel

        val mapView = MapView(activity)
        bind.kakaomapMissionPoint.addView(mapView)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.544433, 127.056729), true)
        mapView.setCalloutBalloonAdapter(MissionBalloonAdapter(layoutInflater))

        if (receiveData == "NearMission")
            setNearMissionPoint(mapView)
        else if (receiveData == "RegionMission")
            setRegionMissionPoint(mapView)

        return bind.root
    }

    fun onClickClose(view: View) {
//        parentFragmentManager.beginTransaction()
//            .remove(this).commit()
//        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.vertical_enter, R.anim.vertical_exit)
            .remove(this)
            .commit()
    }

    private fun setRegionMissionPoint(mapView: MapView) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5452417439977, 127.056686462112), true) //중심점 변경
        mapView.setZoomLevel(5, true)

        // 중심점 변경 + 줌 레벨 변경
//        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true)

        var bitmapDrawable = writeOnDrawable(requireContext().applicationContext, R.drawable.custom_marker2, "테스트")
        val bitmap = bitmapDrawable?.bitmap

        MapPOIItem().apply { //성동구청
            itemName = "테스트"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5634225092469, 127.036964999975)
            markerType = MapPOIItem.MarkerType.CustomImage              //마커모양
            customImageBitmap = bitmap
            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
            mapView.addPOIItem(this)
        }

        MapPOIItem().apply { //광진구청
            itemName = "테스트"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5385399017325, 127.081912437816)
            markerType = MapPOIItem.MarkerType.CustomImage              //마커모양
            customImageBitmap = bitmap
            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
            mapView.addPOIItem(this)
        }

        MapPOIItem().apply { //강남구청
            itemName = "테스트"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5179681611717, 127.047059839521)
            markerType = MapPOIItem.MarkerType.CustomImage              //마커모양
            customImageBitmap = bitmap
            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
            mapView.addPOIItem(this)
        }


    }

    fun writeOnDrawable(context: Context, drawableId: Int, text: String): BitmapDrawable? {

        // dpi 측정
        val metrics = DisplayMetrics()
        val mgr = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mgr.defaultDisplay.getMetrics(metrics)

        // dpi에 따른 텍스트 기본 크기 설정
        val dpi_text_size: Float = metrics.densityDpi * DEFAULT_TEXT_SIZE / XXXHDPI

        // 텍스트 길이에 따른 텍스트 크기 설정 ( 3자리 이상은 힘듬 )
        val textSize = dpi_text_size - dpi_text_size / 4f * (text.length - 1)

        // 마커 이미지
        val bm = BitmapFactory.decodeResource(context.resources, drawableId)
            .copy(Bitmap.Config.ARGB_8888, true)

        // 텍스트 스타일
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.color = Color.WHITE
        paint.textSize = textSize

        // 텍스트 위치 (가운데 정렬)
        val textPositionX: Float = (bm.width - paint.measureText(text)) / 2f
        val addAlpha: Float = DEFAULT_TEXT_SIZE / 6 * metrics.densityDpi / XXXHDPI
        val textPositionY: Float = (bm.height / 2).toFloat() + ( if (text.length == 1) addAlpha else 0 ).toFloat()

        // 텍스트 입력
        val canvas = Canvas(bm)
        canvas.drawText(text, textPositionX, textPositionY, paint)
        return BitmapDrawable(bm)
    }

    private fun setNearMissionPoint(mapView: MapView) {

        //마커 추가
        MapPOIItem().apply {
            itemName = "미션1"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5452417439977, 127.056686462112)
            markerType = MapPOIItem.MarkerType.BluePin
            mapView.addPOIItem(this)
            //커스텀 마커
//            markerType = MapPOIItem.MarkerType.BluePin              //마커모양
//            customImageResourceId = R.drawable.marker_blue          //커스텀마커이미지
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  //클릭시마커모양
//            customSelectedImageResourceId = R.drawable.marker_red   //클릭시마커이미지
//            isCustomImageAutoscale = false                          //커스텀마커 이미지 크기 자동조정
//            setCustomImageAnchor(0.5f,1.0f) //마커 이미지 기준점
        }


        val missionPoint2 = MapPOIItem().apply {
            itemName = "미션2"
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5446495068023, 127.061392779847)
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin  //클릭시마커모양
        }
        mapView.addPOIItem(missionPoint2)
    }

    class MissionBalloonAdapter(inflater: LayoutInflater) : CalloutBalloonAdapter {
        val mCalloutBalloon: View = inflater.inflate(R.layout.balloon_point, null)
        val name: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_name)
        val address: TextView = mCalloutBalloon.findViewById(R.id.balloon_tv_address)

        override fun getCalloutBalloon(poiItem: MapPOIItem?): View {
            var geocoder: Geocoder

            // 마커 클릭 시 나오는 말풍선
            name.text = "주소표시예정"   // 해당 마커의 정보 이용 가능
            return mCalloutBalloon
        }

        override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
            // 말풍선 클릭 시
            address.text = "getPressedCalloutBalloon"
            return mCalloutBalloon
        }


    }

}