package com.nathansdev.newsfeed.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.transition.Slide
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

object Utils {
    const val NEW_API = "NewApi"

    /**
     * @param context           UI context.
     * @param drawableVectorRes Drawable vector resource.
     * @param imageView         Image view.
     */
    fun setTintedVectorAsset(context: Context, @DrawableRes drawableVectorRes: Int,
                             imageView: ImageView) {
        imageView.setImageDrawable(getTintedVectorAsset(context, drawableVectorRes))
    }

    fun getTintedVectorAsset(context: Context, drawableVectorRes: Int): Drawable? {
        val nonWhite = VectorDrawableCompat.create(context.resources,
                drawableVectorRes, context.theme)
        val white = DrawableCompat.wrap(nonWhite!!)
        return white
    }

    /**
     * Slide Transition
     *
     * @param rootView Scene root.
     */
    @SuppressLint(NEW_API)
    fun captureTransitionSlide(rootView: ViewGroup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(rootView, Slide())
        }
    }

}