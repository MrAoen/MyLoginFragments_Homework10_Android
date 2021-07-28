package com.example.myloginfragments_homework10_android

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit


class SplashscreenFragment : Fragment() {

    private var login_name: String? = null
    private var login_pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login_name = it.getString("login_name")
            login_pass = it.getString("login_pass")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            if (login_name == null || login_pass == null) {
                parentFragmentManager.commit {
                    replace(R.id.container, LoginFragment()).addToBackStack(null)
                }
            } else {
                parentFragmentManager.commit {
                    val resultFragment = ResultFragment()
                    resultFragment.arguments = arguments
                    replace(R.id.container, resultFragment).addToBackStack(null)
                }
            }
        }, 2000)
    }
}