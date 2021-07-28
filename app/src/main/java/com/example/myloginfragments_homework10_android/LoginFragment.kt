package com.example.myloginfragments_homework10_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.textfield.TextInputLayout

private const val ARG_PARAM1 = "login_name"
private const val ARG_PARAM2 = "login_pass"

class LoginFragment : Fragment() {

    private var name: String? = null
    private var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            pass = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = view.findViewById<TextInputLayout>(R.id.loginUserName)
        val password = view.findViewById<TextInputLayout>(R.id.loginUserPassword)

        password.editText?.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if (validateMe(user, password)) {
                        val prefs = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
                        prefs?.edit()
                            ?.putString("login_name", user.editText?.text.toString())
                            ?.putString("login_pass", password.editText?.text.toString())
                            ?.apply()
                        parentFragmentManager.commit {
                            replace(R.id.container, ResultFragment()).addToBackStack(null)
                        }

                    }
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    private fun validateMe(user: TextInputLayout, password: TextInputLayout): Boolean {
        var result = true
        if (user.editText?.text.toString().length < 6) {
            user.error = "User name must be at least 6 char lenght"
            result = false
        } else {
            user.error = null
        }

        if (password.editText?.text.toString().length < 6) {
            password.error = "Require any password"
            result = false
        } else {
            password.error = null
        }
        return result
    }
}