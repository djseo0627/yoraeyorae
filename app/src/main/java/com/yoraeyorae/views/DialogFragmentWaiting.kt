package com.yoraeyorae.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.DialogFragmentWaitingBinding
import com.yoraeyorae.viewmodel.DialogFragmentWaitingViewModel

class DialogFragmentWaiting : DialogFragment() {

    private val viewModel: DialogFragmentWaitingViewModel by viewModels()
    private lateinit var bind : DialogFragmentWaitingBinding
    private lateinit var clickListener: AlertClickListener

    companion object {
        private const val ARG_TITLE = "ATG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val REQUEST_CODE = 1002

        fun dialogFragmentWaiting(
//            sender : Fragment? = null,
            title: String,
            message: String,
        ): DialogFragmentWaiting {
            return DialogFragmentWaiting().apply {
                arguments = bundleOf(
                    ARG_TITLE to title,
                    ARG_MESSAGE to message,
                )

//                if(sender != null)
//                    setTargetFragment(sender, REQUEST_CODE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_waiting, container, false)
        bind.viewModel = viewModel
        bind.view = this
        bind.lifecycleOwner = this

        setupViews()
//        return inflater.inflate(R.layout.dialog_fragment_waiting, container, false)
        return bind.root
    }


    interface AlertClickListener {
        fun onClickConfirm(view: View)
        fun onClickCancel(view: View)
    }

    fun setAlertClickListener(alertClickListener : AlertClickListener) {
        this.clickListener = alertClickListener
    }

    fun onClickConfirm(view: View) {
        clickListener.onClickConfirm(view)
        dismiss()
    }

    fun onClickCancel(view: View) {
        clickListener.onClickCancel(view)
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    private fun setupViews() {
        dialog.apply {
//            bind.tvAlertTitle.text = requireArguments().getString(DialogFragmentAlert.ARG_TITLE)
//            bind.tvMessage.text = requireArguments().getString(DialogFragmentAlert.ARG_MESSAGE)
        }
    }

}