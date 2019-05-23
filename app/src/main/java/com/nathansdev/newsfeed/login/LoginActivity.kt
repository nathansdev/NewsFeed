package com.nathansdev.newsfeed.login

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
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
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_login)
        setUpViews()
    }

    private fun setUpViews() {
        btn_login.setOnClickListener { onLoginClicked() }
        changeStatusBarBackground()
    }

    private fun onLoginClicked() {
        btn_login.isEnabled = false
        progress_mask.visibility = View.VISIBLE
        if (validate()) {
            btn_login.isEnabled = true
            appPreferences.saveUserNameAndPassword(name_edit_text.text.toString(), password_edit_text.text.toString())
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

        val name = name_edit_text.text.toString()
        val password = password_edit_text.text.toString()

        if (name.isEmpty()) {
            name_edit_text.error = "Username should have t least eight characters"
            valid = false
        } else {
            name_edit_text.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            password_edit_text.error = "Password should be between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            password_edit_text.error = null
        }
        return valid
    }

    private fun changeStatusBarBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}