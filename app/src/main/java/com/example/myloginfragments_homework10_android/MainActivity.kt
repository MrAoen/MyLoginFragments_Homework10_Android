package com.example.myloginfragments_homework10_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("login", MODE_PRIVATE)
        val login_name = prefs.getString("login_name", null)
        val login_pass = prefs.getString("login_pass", null)

        val splashscreenFragment = SplashscreenFragment()
        splashscreenFragment.arguments = bundleOf(
            "login_name" to login_name,
            "login_pass" to login_pass
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.container, splashscreenFragment)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}