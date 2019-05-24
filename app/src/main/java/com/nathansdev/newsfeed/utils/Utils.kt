package com.nathansdev.newsfeed.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.transition.Slide
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.nathansdev.newsfeed.GlideApp
import com.nathansdev.newsfeed.R

/**
 * app utils class
 */
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
     * Load round image into imageview.
     *
     * @param context ui context.
     * @param url     image url.
     * @param iv      image view.
     */
    fun loadRoundImage(context: Context, url: String, iv: ImageView?) {
        GlideApp.with(context).asBitmap()
                .load(url)
                .placeholder(R.drawable.abc_btn_check_material)
                .error(R.drawable.abc_btn_check_material)
                .centerCrop()
                .into(object : BitmapImageViewTarget(iv) {
                    override fun setResource(resource: Bitmap?) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        iv?.setImageDrawable(circularBitmapDrawable)
                    }
                })
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