package com.rsschool.quiz.themes

import com.rsschool.quiz.R

object ThemesManager {
    private val themes =
        intArrayOf(
            R.style.Theme_Quiz_First,
            R.style.Theme_Quiz_Second,
            R.style.Theme_Quiz_Third,
            R.style.Theme_Quiz_Fourth,
            R.style.Theme_Quiz_Fifth,
            R.style.Theme_Quiz_Sixth
        )
    private var shuffled = false

    fun getTheme(questionNumber: Int): Int {
        if (!shuffled) {
            themes.shuffle()
            shuffled = true
        }
        return themes[questionNumber % themes.size]
    }
}