package com.yoraeyorae.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.DialogFragmentMissionInfoBinding
import com.yoraeyorae.viewmodel.DialogFragmentMissionInfoViewModel

class DialogFragmentMissionInfo : DialogFragment() {

    private lateinit var bind : DialogFragmentMissionInfoBinding
    private val fragmentMissionInfoViewModel: DialogFragmentMissionInfoViewModel by viewModels()
    private lateinit var missionClickListener: MissionClickListener

    interface MissionClickListener {
        fun onMissionAccept()
        fun onMissionReject()
    }

    fun setMissionClickListener(missionClickListener : MissionClickListener) {
        this.missionClickListener = missionClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.btnAlarmAccept.setOnClickListener {
            missionClickListener.onMissionAccept()
            dismiss()
        }

        bind.btnAlarmReject.setOnClickListener {
            missionClickListener.onMissionReject()
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_mission_info, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = fragmentMissionInfoViewModel

        return bind.root
    }

}