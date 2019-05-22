package com.nathansdev.newsfeed.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nathansdev.newsfeed.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base Activity class that any Activity extends.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, MvpView {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessageInDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(this)
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton(R.string.ok) { dialog, which -> dialog.dismiss() }
                .setCancelable(true)
        dialogBuilder.show()
    }
}