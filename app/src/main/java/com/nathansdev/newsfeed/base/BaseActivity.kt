package com.nathansdev.newsfeed.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Base Activity class that any Activity extends.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showMessageInToast(message: String) {
    }

    override fun showMessageInDialog(message: String) {
    }
}