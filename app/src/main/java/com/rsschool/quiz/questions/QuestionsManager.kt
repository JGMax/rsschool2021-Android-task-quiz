package com.rsschool.quiz.questions

import android.content.Context
import com.rsschool.quiz.R

object QuestionsManager {
    private var questions = listOf<Question>()

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

    fun getResult() =
        (questions.filter { it.checkAnswer() }.size.toFloat() / questions.size) * 100

    fun getMessage(ctx: Context) : String {
        var msg = """
            ${ctx.getString(R.string.result)}: ${getResult()}
            
            
        """.trimIndent()
        questions.forEach {
            msg += it.text + "\n"
            msg += "${ctx.getString(R.string.your_answer)} ${it.answer}\n"
            msg += "${ctx.getString(R.string.correct_answer)} ${it.correctAnswer}\n\n"
        }
        return msg.trimIndent()
    }

    fun hasNext(questionIdx: Int) = questionIdx in 0 until questions.lastIndex
}