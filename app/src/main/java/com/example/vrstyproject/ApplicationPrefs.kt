package com.example.vrstyproject

import android.content.SharedPreferences
import com.example.vrstyproject.welcome.App

class ApplicationPrefs {

    private val mPreferences: SharedPreferences = App.self().getSharedPreferences(
        Constants.APPLICATION_PREFS, 0
    )

    fun isNotFirstTime(): Boolean {
        return mPreferences.getBoolean(Constants.IS_FIRST_TIME, false)
    }

    fun setNotFirstTime(b: Boolean) {
        val mEditor = mPreferences.edit()
        mEditor.putBoolean(Constants.IS_FIRST_TIME, b).apply()
    }
}