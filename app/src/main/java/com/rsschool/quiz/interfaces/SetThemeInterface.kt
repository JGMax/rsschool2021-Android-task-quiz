package com.rsschool.quiz.interfaces

import android.content.Context

interface SetThemeInterface {
    fun setTheme(ctx: Context?, themeId: Int) {
        ctx?.setTheme(themeId)
        (ctx as? ChangeThemeInterface)?.changeTheme(themeId)
    }
}