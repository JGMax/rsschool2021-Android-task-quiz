package com.rsschool.quiz.questions

class QuestionModel {
    var text = ""
    var answers = arrayOf<String>()
    var correctAnswer = ""
    var selectedAnswer = -1
    val answer: String
        get() {
            return if (selectedAnswer in answers.indices) {
                answers[selectedAnswer]
            } else {
                ""
            }
        }

    fun checkAnswer(): Boolean {
        if (selectedAnswer in answers.indices) {
            return answers[selectedAnswer] == correctAnswer
        }
        return false
    }
}