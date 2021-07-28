package com.example.myloginfragments_homework10_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text


class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val user = view.findViewById<TextView>(R.id.userName)
        user.text = arguments?.getString("login_name")

        val pass = view.findViewById<TextView>(R.id.userPassword)
        pass.text = arguments?.getString("login_pass")

        return view
    }

}