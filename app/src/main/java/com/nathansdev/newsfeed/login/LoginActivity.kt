package com.nathansdev.newsfeed.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.nathansdev.newsfeed.R
import com.nathansdev.newsfeed.base.BaseActivity
import com.nathansdev.newsfeed.home.HomeActivity
import com.nathansdev.newsfeed.storage.AppPreferences
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


/**
 *  Login Screen with username and password check.
 */
class LoginActivity : BaseActivity() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViews()
    }

    private fun setUpViews() {
        btn_login.setOnClickListener { onLoginClicked() }
    }

    private fun onLoginClicked() {
        btn_login.isEnabled = false
        progress_mask.visibility = View.VISIBLE
        if (validate()) {
            btn_login.isEnabled = true
            appPreferences.saveUserNameAndPassword(input_name.text.toString(), input_password.text.toString())
            appPreferences.setIsLoggedIn(true)
            Handler().postDelayed(Runnable {
                progress_mask.visibility = View.GONE
                showMessageInToast("Log in completed successfully")
                routeToHome()
            }, 2000)
        } else {
            showMessageInToast("Login Failed!")
            btn_login.isEnabled = true
            progress_mask.visibility = View.GONE
        }
    }

    private fun routeToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validate(): Boolean {
        var valid = true

        val name = input_name.text.toString()
        val password = input_password.text.toString()

        if (name.isEmpty()) {
            input_name.error = "Username should have t least eight characters"
            valid = false
        } else {
            input_name.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            input_password.error = "Password should be between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            input_password.error = null
        }

        return valid
    }
}