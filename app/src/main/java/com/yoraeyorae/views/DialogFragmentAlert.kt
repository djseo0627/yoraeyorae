package com.yoraeyorae.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yoraeyorae.R
import com.yoraeyorae.databinding.DialogFragmentAlertBinding
import com.yoraeyorae.viewmodel.DialogFragmentAlertViewModel
import com.yoraeyorae.viewmodel.ActivityMainViewModel

class DialogFragmentAlert : DialogFragment() {

    private val viewModelFragment: DialogFragmentAlertViewModel by viewModels()
    private val activityMainViewModel: ActivityMainViewModel by activityViewModels()
    lateinit var bind: DialogFragmentAlertBinding
//    private val str = str
//    private var listener: DialogResultListener? = null
    private val listener = hashSetOf<DialogResultListener>()

    companion object {
        private const val ARG_TITLE = "ATG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val REQUEST_CODE = 1002

        fun dialogFragmentAlert(
//            sender : Fragment? = null,
            title: String,
            message: String,
        ): DialogFragmentAlert {
            return DialogFragmentAlert().apply {
                arguments = bundleOf(
                    ARG_TITLE to title,
                    ARG_MESSAGE to message,
                )

//                if(sender != null)
//                    setTargetFragment(sender, REQUEST_CODE)
            }
        }
    }

    private lateinit var alertClickListener: AlertClickListener

    interface AlertClickListener {
        fun onClickConfirm(view: View)
        fun onClickCancel(view: View)
    }

    fun setAlertClickListener(alertClickListener : AlertClickListener) {
        this.alertClickListener = alertClickListener
    }

    fun onClickConfirm(view: View) {
        alertClickListener.onClickConfirm(view)
        dismiss()
    }

    fun onClickCancel(view: View) {
        alertClickListener.onClickCancel(view)
        dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if ( context is DialogResultListener) {
//            listener.add(context)
//            return
//        }
//
//        if (targetFragment is DialogResultListener) {
//            listener.add(targetFragment as DialogResultListener)
//            return
//        }
//        if (listener == null) {
//            Toast.makeText(requireContext().applicationContext, "Did you forget to implement dialog interface?", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_alert, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModelFragment
        bind.view = this

        setupViews()

        return bind.root
    }

    private fun setupViews() {
        dialog.apply {
            bind.tvAlertTitle.text = requireArguments().getString(ARG_TITLE)
            bind.tvMessage.text = requireArguments().getString(ARG_MESSAGE)
        }
//        bind.ivAlertConfirm.setOnClickListener{
//            onConfirmClicked()
//        }
//        bind.ivAlertCancel.setOnClickListener {
//            onCancelClicked()
//        }
    }

//    private fun onConfirmClicked() {
//        requireDialog().dismiss()
//        listener.forEach{
//            it.onDialogResult(DialogResult.Yes("Hello"))
//        }
//    }
//    private fun onCancelClicked() {
//        requireDialog().dismiss()
//        listener.forEach {
//            it.onDialogResult(DialogResult.No)
//        }
//    }

}