package com.yoraeyorae.views

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yoraeyorae.R
import com.yoraeyorae.databinding.FragmentLoginBinding
import com.yoraeyorae.views.ActivityMain

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var title: String? = null
    private lateinit var bind: FragmentLoginBinding

    lateinit var navController: NavController

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
//            title = it.getString("title")
//        }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            title = it.getString("title")
//        }
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        bind.tvSignup.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        bind.tvFind.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        bind.tvSignup.setOnClickListener(this)
        bind.tvFind.setOnClickListener(this)
        bind.btnLogin.setOnClickListener(this)
        bind.editTextEmailAddress.setText("test")
        bind.editTextPassword.setText("1234")
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tvSignup -> {
                    navController.navigate(R.id.action_loginFragment_to_signupFragment)
                }
                R.id.tvFind -> {
//                    (activity as LoginActivity).showAlert("안내말씀", "준비중입니다.")
//                    showAlert()
                }
                R.id.btnLogin -> {
                    if (bind.editTextEmailAddress.text.toString() == "test"
                        && bind.editTextPassword.text.toString() == "1234"
                    ) {
                        val intent = Intent(activity, ActivityMain::class.java)
                        startActivity(intent)
                        (activity as ActivityLogin).finish()
                    }
                }
            }
        }
    }


//    private fun showAlert() {
//        val dialog = DialogAlertFragment("테스트테스트")
//        dialog.show(
//            childFragmentManager, "MissionInfoFragment"
//        )
//    }

}