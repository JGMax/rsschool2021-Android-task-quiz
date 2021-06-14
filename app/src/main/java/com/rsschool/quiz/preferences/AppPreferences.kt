package com.rsschool.quiz.preferences

import android.content.Context

class AppPreferences(private val ctx: Context) {
    private companion object {
        const val BEST_RESULT_KEY = "BEST_RESULT_KEY"
        const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        const val PREFERENCES_NAME = "APP_PREFERENCES"
    }

    fun setResult(newResult: Float) {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val max = getBestResult()
        val editor = preferences.edit()
        editor.putFloat(PREVIOUS_RESULT_KEY, newResult)
        if (max < newResult) {
            editor.putFloat(BEST_RESULT_KEY, newResult)
        }
        editor.apply()
    }

    fun deleteResults() {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences
            .edit()
            .putFloat(PREVIOUS_RESULT_KEY, 0.0f)
            .putFloat(BEST_RESULT_KEY, 0.0f)
            .apply()
    }

    fun getBestResult() : Float {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getFloat(BEST_RESULT_KEY, 0.0f)
    }

    fun getPreviousResult() : Float {
        val preferences = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getFloat(PREVIOUS_RESULT_KEY, 0.0f)
    }
}