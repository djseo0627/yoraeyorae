package com.yoraeyorae.views

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.RecyclerChattingAdapter
import com.yoraeyorae.adapters.recycler.data.RecyclerChattingData
import com.yoraeyorae.databinding.FragmentChattingRoomBinding
import com.yoraeyorae.viewmodel.FragmentChattingRoomViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.currentCoroutineContext
import java.text.SimpleDateFormat
import java.util.*

class FragmentChattingRoom : Fragment() {

    private val viewModel: FragmentChattingRoomViewModel by viewModels()
    private lateinit var bind: FragmentChattingRoomBinding
    private lateinit var navController: NavController

    private var currentUser : String =""
    private var chatData = mutableListOf<RecyclerChattingData>()
    private lateinit var chatAdapter: RecyclerChattingAdapter

    private var step = 0

    private var imageCaptured = false
    private val launcher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            if (it != null) {
//                bind.ivCaptureImage.setImageBitmap(it)
                chatAdapter.addItem(RecyclerChattingData(user=currentUser, text ="", time= getTime(), type = "image", img = it))
                imageCaptured = true
            } else {
                Toast.makeText(requireContext().applicationContext, "사진촬영 실패!!", Toast.LENGTH_SHORT).show()
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val curMode : Boolean = (activity as ActivityMain).getCurrentMode()
        currentUser = if(curMode) "등록자"
        else "수행자"

        if (requireContext().applicationContext != null) {
            chatAdapter = RecyclerChattingAdapter(currentUser)
            bind.rvChatting.adapter = chatAdapter
            bind.rvChatting.setHasFixedSize(true)
        }

        val bitmap = BitmapFactory.decodeResource((activity as ActivityMain).resources, R.drawable.mission_order)
        if(curMode) {
            bind.containerRegisterButtons.visibility = View.VISIBLE
            chatAdapter.addItem(RecyclerChattingData(user = "등록자", text = "", time = getTime(), type = "image", img = bitmap))
        }
        else {
            bind.containerPerformerButtons.visibility = View.VISIBLE
            chatAdapter.addItem(RecyclerChattingData(user = "등록자", text = "", time = getTime(), type = "image", img = bitmap))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_chatting_room, container, false)
        bind.view = this
        bind.viewModel = viewModel
        bind.lifecycleOwner = this

        (activity as ActivityMain).setNavigationVisibility(false)

        return bind.root
    }

    private fun getTime(): String {
        val timestamp: Timestamp = Timestamp.now()
        val sf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA)
        sf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return sf.format(timestamp.toDate())
    }

    fun onClickArrive(view : View) {
        chatAdapter.addItem(RecyclerChattingData(user = currentUser, text = "미션시작지도착", time = getTime()))
        step++
    }

    fun onClickMany(view : View) {
        chatAdapter.addItem(RecyclerChattingData(user = currentUser, text = "사람이 많아요", time = getTime()))
    }

    fun onClickChange(view : View) {
        chatData.apply{
            add(RecyclerChattingData(user = currentUser, text = "교환/누락신청이 접수되었습니다.", time = getTime()))
            add(RecyclerChattingData(user = currentUser, text = "라면이 없어요", time = getTime()))
        }
        chatAdapter.addAllItem(chatData)
    }

    fun onClickPurchaseDone(view : View) {
        chatAdapter.addItem(RecyclerChattingData(user = currentUser, text = "구매가 완료되었습니다.\n영수증을 확인해주세요", time = getTime()))
        launcher.launch()
        onDetach()
        chatAdapter.addItem(RecyclerChattingData(user = currentUser, text = "물품 구매금액은 10,000원입니다.", time = getTime()))
    }

    fun onClickAccomplished(view : View) {
        chatAdapter.addItem(RecyclerChattingData(user = currentUser, text = "충전금액이 부족합니다.\n충전하기를 이용해주세", time = getTime()))
    }

    fun onClickSendSetHardCodeData(view: View) {
        val msg = bind.etChat.text.toString()
        bind.etChat.text.clear()
//        chatData.add(RecyclerChattingData(user = "등록자", text = msg, time = getTime()))
        chatAdapter.addItem(RecyclerChattingData(user = "등록자", text = msg, time = getTime(), type = "text", img = null))
    }

    fun onClickBtnCancel(view : View) {
        Toast.makeText(requireContext().applicationContext, "미션취소 기능 준비중입니다.", Toast.LENGTH_SHORT).show()
    }

    fun onClickCamera(view : View) {
        launcher.launch()
        onDetach()
    }

    fun onClickLocationCheck(view : View) {
        bind.containerMapView.visibility = View.VISIBLE

        val fragment =  FragmentMissionMap.newInstance("NearMission")

        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.vertical_enter, R.anim.horizon_exit)
            .replace(R.id.container_map_view, fragment)
            .commit()
    }


}