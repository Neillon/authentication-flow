package com.neillon.network.utils

import android.content.Context
import com.neillon.network.R

object TokenUtils {

    fun getToken(context: Context?): String {
        return context?.let { thisContext ->
            thisContext.apply {
                val sharedPrefs =
                    getSharedPreferences(getString(R.string.authentication), Context.MODE_PRIVATE)

                sharedPrefs.getString(getString(R.string.user_token), "")
            }
        }.toString()
    }

}
