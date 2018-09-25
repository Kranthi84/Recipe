package com.recipe.kchinnak.searchrecipe.utils

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.recipe.kchinnak.searchrecipe.R
import java.io.IOException
import java.io.InputStream
import java.util.*

class ConfigUtil {

    companion object {
        val TAG: String = ConfigUtil::class.java.simpleName
    }

    fun getConfigValue(mContext: Context, key: String): String? {

        val res = mContext.resources

        try {
            val inputStream: InputStream = res.openRawResource(R.raw.config)
            var props = Properties()
            props.load(inputStream)
            return props.getProperty(key)
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, e.localizedMessage)
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage)
        }

        return null

    }
}