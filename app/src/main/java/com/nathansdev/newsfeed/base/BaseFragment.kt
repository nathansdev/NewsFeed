package com.nathansdev.newsfeed.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.nathansdev.newsfeed.R
import dagger.android.support.AndroidSupportInjection

/**
 * Base fragment class that any fragment extends.
 */
abstract class BaseFragment : Fragment(), MvpView {
    private var baseActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this)
        }
        if (context is BaseActivity) {
            this.baseActivity = context
        }
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
    }

    override fun onDestroyView() {
        baseActivity = null
        super.onDestroyView()
    }

    protected abstract fun setUpView(view: View)

    override fun showMessageInToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessageInDialog(message: String) {
        val dialogBuilder = baseActivity?.let {
            AlertDialog.Builder(it)
                    .setTitle("Alert!")
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, { dialog, which -> dialog.dismiss() })
                    .setCancelable(true)
        }
        dialogBuilder?.show()
    }

    fun getBaseActivity(): BaseActivity? {
        return baseActivity
    }
}