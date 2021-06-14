package com.rsschool.quiz.questions

object QuestionsManager {
    private var questions = listOf<Question>()

    fun isEmpty() : Boolean = questions.isEmpty()

    fun isNotEmpty() : Boolean = questions.isNotEmpty()

    fun clearAnswers() {
        questions.forEach {
            it.selectedAnswer = -1
        }
    }

    fun setQuestions(questions: List<Question>) {
        questions.forEach {
            it.answers.shuffle()
        }
        this.questions = questions.shuffled()
    }

    fun getQuestion(idx: Int) : Question? {
        if (idx in questions.indices) {
            return questions[idx]
        }
        return null
    }

    fun checkAnswer(questionIdx: Int, answerIdx: Int) : Boolean {
        if (questionIdx in questions.indices) {
            return questions[questionIdx].checkAnswer(answerIdx)
        }
        return false
    }

    fun hasNext(questionIdx: Int) = questionIdx in 0 until questions.lastIndex
}