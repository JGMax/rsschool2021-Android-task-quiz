package com.rsschool.quiz.interfaces

import android.content.Context
import androidx.annotation.StyleRes

interface FragmentThemeChanger {
    fun setTheme(ctx: Context?, @StyleRes themeId: Int) {
        ctx?.setTheme(themeId)
        (ctx as? ActivityThemeChanger)?.changeTheme(themeId)
    }
}