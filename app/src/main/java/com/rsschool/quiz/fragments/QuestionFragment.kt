package com.rsschool.quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityChanger
import com.rsschool.quiz.interfaces.OnBackPressedFragmentListener
import com.rsschool.quiz.interfaces.FragmentThemeChanger
import com.rsschool.quiz.interfaces.TitleChanger
import com.rsschool.quiz.questions.QuestionModel
import com.rsschool.quiz.questions.QuestionsManager
import com.rsschool.quiz.themes.ThemesManager

class QuestionFragment : Fragment(), FragmentThemeChanger {
    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding
        get() = requireNotNull(_binding)

    private var questionIdx = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val args: QuestionFragmentArgs by navArgs()
        questionIdx = args.numberOfQuestion

        setTheme(context, ThemesManager.getTheme(questionIdx))

        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        with(binding) {
            val question = QuestionsManager.getQuestion(questionIdx)
            generateRadioButtons(question)

            if (!QuestionsManager.hasNext(questionIdx)) {
                nextButton.text = getString(R.string.submit)
            }
            if (radioGroup.checkedRadioButtonId == -1) {
                nextButton.isEnabled = false
            }
            nextButton.setOnClickListener { onNextClick() }
            previousButton.setOnClickListener { onPreviousClick() }
        }
        setTitle()
        setBackButtonsVisibility()

        return binding.root
    }

    private fun setTitle() {
        (context as? TitleChanger)
            ?.changeTitle("${getString(R.string.question)} ${questionIdx + 1}")
    }

    private fun setBackButtonsVisibility() {
        with(binding) {
            if (questionIdx == 0) {
                (context as? BackButtonVisibilityChanger)
                    ?.setBackButtonVisibility(false)
                previousButton.visibility = View.GONE
            } else {
                (context as? BackButtonVisibilityChanger)
                    ?.setBackButtonVisibility(true)
            }
        }
    }

    private fun generateRadioButtons(questionModel: QuestionModel?) {
        with(binding) {
            questionModel?.let {
                questionView.text = it.text
                it.answers.forEach { ans ->
                    val button = RadioButton(context)
                    button.text = ans
                    radioGroup.addView(button)
                }
                if (it.selectedAnswer != -1) {
                    radioGroup.check(radioGroup[0].id + it.selectedAnswer)
                }
                radioGroup.setOnCheckedChangeListener { group, checked ->
                    val selectedIdx = checked - group[0].id
                    QuestionsManager.getQuestion(questionIdx)?.selectedAnswer = selectedIdx
                    nextButton.isEnabled = true
                }
            }
        }
    }

    private fun onPreviousClick() {
        (context as? OnBackPressedFragmentListener)?.onBackPressedFragment()
    }

    private fun onNextClick() {
        if (QuestionsManager.hasNext(questionIdx)) {
            val action = QuestionFragmentDirections
                .actionToQuestion(questionIdx + 1)
            findNavController().navigate(action)
        } else {
            val action = QuestionFragmentDirections.actionToEnd()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        binding.radioGroup.removeAllViews()
        _binding = null
        super.onDestroyView()
    }
}