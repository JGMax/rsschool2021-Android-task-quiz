package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.dialogs.DialogIds
import com.rsschool.quiz.interfaces.BackButtonVisibilityChanger
import com.rsschool.quiz.interfaces.ActivityThemeChanger
import com.rsschool.quiz.interfaces.OnBackPressedFragmentListener
import com.rsschool.quiz.interfaces.TitleChanger
import com.rsschool.quiz.questions.QuestionModel
import com.rsschool.quiz.questions.QuestionsManager
import com.rsschool.quiz.dialogs.getDialog


class MainActivity : AppCompatActivity(), BackButtonVisibilityChanger,
    OnBackPressedFragmentListener, TitleChanger,
    ActivityThemeChanger {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var controller: NavController? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)

            setSupportActionBar(toolbarInclude.toolbar)
            setBackButtonVisibility(false)
            toolbarInclude.toolbar.setNavigationOnClickListener {
                onBackPressedFragment()
            }
            controller = findNavController(R.id.container)
        }
        QuestionsManager.setQuestions(createQuestions())
        changeTheme(R.style.Theme_Quiz_Start)
    }

    private fun createQuestions(): Array<QuestionModel> {
        val question1 = QuestionModel()
        question1.text = "What is the largest animal?"
        question1.answers = arrayOf("Elephant", "Monkey", "Me", "Dog", "Bee", "Java", "Car")
        question1.correctAnswer = "Elephant"

        val question2 = QuestionModel()
        question2.text = "What is the meaning of life?"
        question2.answers = arrayOf("42", "Developing", "Money", "Family", "Love")
        question2.correctAnswer = "42"

        val question3 = QuestionModel()
        question3.text = "What is my name?"
        question3.answers = arrayOf("Max", "Mark", "Jarvis", "Ivan", "Nadya")
        question3.correctAnswer = "Max"

        val question4 = QuestionModel()
        question4.text = "What is the best language?"
        question4.answers = arrayOf("English", "Russian", "Kotlin", "Java", "Esperanto", "Python")
        question4.correctAnswer = "Kotlin"

        val question5 = QuestionModel()
        question5.text = "How are you?"
        question5.answers = arrayOf(
            "It has been better", "Not so bad", "Best of the best",
            "I am a king of my life", "I am tired of learning"
        )
        question5.correctAnswer = "I am a king of my life"

        val question6 = QuestionModel()
        question6.text = "Would you give 100 points to this app?"
        question6.answers = arrayOf(
            "Yes, of course", "No, it has some problems",
            "No, I want to be the first", "No, I do not like this design", "No, I am angry"
        )
        question6.correctAnswer = "Yes, of course"

        return arrayOf(question1, question2, question3, question4, question5, question6)
    }

    override fun setBackButtonVisibility(visibility: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }

    override fun onBackPressedFragment() {
        onBackPressed()
    }

    override fun changeTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun changeTheme(id: Int) {
        setTheme(id)

        val typedValue = TypedValue()
        val theme: Resources.Theme = theme

        theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true)
        window.decorView.setBackgroundColor(typedValue.data)

        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(typedValue.data))

        theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = typedValue.data
    }

    override fun onBackPressed() {
        val args = controller?.currentBackStackEntry?.arguments
        if (args?.containsKey(NUMBER_OF_QUESTION_KEY) == true) {
            val numOfQuestion = args.getInt(NUMBER_OF_QUESTION_KEY)
            if (numOfQuestion == 0) {
                getDialog(this,
                    DialogIds.DIALOG_QUIT_FIRST_QUESTION,
                    positiveAction = { super.onBackPressed() })
                return
            }
        }
        super.onBackPressed()
    }

    private companion object {
        const val NUMBER_OF_QUESTION_KEY = "numberOfQuestion"
    }
}