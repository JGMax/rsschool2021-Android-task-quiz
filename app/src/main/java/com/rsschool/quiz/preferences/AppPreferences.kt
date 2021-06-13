package com.rsschool.quiz.preferences

import android.content.Context

class AppPreferences(private val ctx: Context) {
    private companion object {
        const val PREVIOUS_NUMBER_KEY = "PREVIOUS_NUMBER"
        const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        const val PREFERENCES_NAME = "APP_PREFERENCES"
    }

    fun setPreviousResult(previousResult: Float) {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().putFloat(PREVIOUS_RESULT_KEY, previousResult).apply()
    }

    fun getPreviousResult() : Float {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getFloat(PREVIOUS_RESULT_KEY, 0.0f)
    }
}