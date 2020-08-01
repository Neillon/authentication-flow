package com.neillon.network.utils

import android.content.Context
import com.neillon.network.R

class TokenUtils(var context: Context?) {

    fun getToken(): String {
        return context?.let {
            it.apply {
                val sharedPrefs =
                    getSharedPreferences(getString(R.string.authentication), Context.MODE_PRIVATE)

                sharedPrefs.getString(getString(R.string.user_token), "")
            }
        }.toString()
    }

}
