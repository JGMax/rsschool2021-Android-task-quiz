package com.rsschool.quiz

import android.R
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityInterface
import com.rsschool.quiz.interfaces.ChangeThemeInterface
import com.rsschool.quiz.interfaces.OnBackPressedFragmentListener
import com.rsschool.quiz.interfaces.TitleChangeInterface
import com.rsschool.quiz.questions.Question
import com.rsschool.quiz.questions.QuestionsManager


class MainActivity : AppCompatActivity(), BackButtonVisibilityInterface,
    OnBackPressedFragmentListener, TitleChangeInterface,
    ChangeThemeInterface {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

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
        }
        QuestionsManager.setQuestions(createQuestions())
    }

    private fun createQuestions() : List<Question> {
        val question1 = Question()
        question1.text = "What is the largest animal?"
        question1.answers = arrayListOf("Elephant", "Monkey", "Me", "Dog", "Bee", "Java", "Car")
        question1.correctAnswer = "Elephant"

        val question2 = Question()
        question2.text = "What is the meaning of life?"
        question2.answers = arrayListOf("42", "Developing", "Money", "Family", "Love")
        question2.correctAnswer = "42"
        return listOf(question1, question2)
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

        theme.resolveAttribute(R.attr.windowBackground, typedValue, true)
        window.decorView.setBackgroundColor(typedValue.data)

        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(typedValue.data))

        theme.resolveAttribute(R.attr.statusBarColor, typedValue, true)
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = typedValue.data
    }
}