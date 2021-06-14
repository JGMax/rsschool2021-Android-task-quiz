package com.rsschool.quiz.questions

class Question {
    var text = ""
    var answers = arrayListOf<String>()
    var correctAnswer = ""
    var selectedAnswer = -1
    val answer : String
    get() {
        return if(selectedAnswer in answers.indices) {
            answers[selectedAnswer]
        } else {
            ""
        }
    }

    fun checkAnswer() : Boolean {
        if (selectedAnswer in answers.indices) {
            return answers[selectedAnswer] == correctAnswer
        }
        return false
    }
}