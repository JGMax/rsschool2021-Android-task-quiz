package com.rsschool.quiz.questions

class Question {
    var text = ""
    var answers = arrayListOf<String>()
    var correctAnswer = ""
    var selectedAnswer = -1

    fun checkAnswer(idx: Int) : Boolean {
        if (idx in answers.indices) {
            return answers[idx] == correctAnswer
        }
        return false
    }
}