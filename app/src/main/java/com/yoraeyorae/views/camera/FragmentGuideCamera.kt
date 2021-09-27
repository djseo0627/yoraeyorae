package com.yoraeyorae.views.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentGuideCameraBinding

class FragmentGuideCamera : Fragment() {
    private lateinit var bind : FragmentGuideCameraBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_guide_camera, container, false)
        bind.view = this
        bind.lifecycleOwner = this

        return bind.root
    }



}