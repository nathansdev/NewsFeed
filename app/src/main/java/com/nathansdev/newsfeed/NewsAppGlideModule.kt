package com.nathansdev.newsfeed

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Glide module for this app.
 */
@GlideModule
class NewsAppGlideModule : AppGlideModule() {

    /**
     * Disabling manifest parsing to avoid adding same module twice.
     *
     * @return true or false.
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
