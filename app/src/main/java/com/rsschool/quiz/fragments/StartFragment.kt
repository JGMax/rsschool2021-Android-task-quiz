package com.rsschool.quiz.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentStartBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityChanger
import com.rsschool.quiz.interfaces.OnBackPressedFragmentListener
import com.rsschool.quiz.interfaces.FragmentThemeChanger
import com.rsschool.quiz.interfaces.TitleChanger
import com.rsschool.quiz.preferences.AppPreferences
import com.rsschool.quiz.questions.QuestionsManager

class StartFragment : Fragment(), FragmentThemeChanger {
    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = requireNotNull(_binding)

    private val preferences by lazy { AppPreferences(context as Context) }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setTheme(context, R.style.Theme_Quiz_Start)
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        QuestionsManager.clearAnswers()

        with(binding) {
            updatePreviousResultView(preferences.getPreviousResult())
            updateBestResultView(preferences.getBestResult())
            startBtn.setOnClickListener { onStartClick() }
            resetBtn.setOnClickListener { onResetClick() }
            exitBtn.setOnClickListener {
                (context as? OnBackPressedFragmentListener)?.onBackPressedFragment()
            }
        }

        (context as? BackButtonVisibilityChanger)?.setBackButtonVisibility(false)
        (context as? TitleChanger)?.changeTitle(getString(R.string.app_name))
        return binding.root
    }

    private fun onStartClick() {
        if (QuestionsManager.isNotEmpty()) {
            QuestionsManager.shuffle()
            val action = StartFragmentDirections.actionToQuestion(0)
            findNavController().navigate(action)
        } else {
            Toast
                .makeText(context, getString(R.string.no_questions), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun onResetClick() {
        preferences.deleteResults()
        updatePreviousResultView(0.0f)
        updateBestResultView(0.0f)
    }

    @SuppressLint("SetTextI18n")
    private fun updatePreviousResultView(newResult: Float) {
        binding.previousResult.text = "${getString(R.string.previous_result)} $newResult%"
    }

    @SuppressLint("SetTextI18n")
    private fun updateBestResultView(newResult: Float) {
        binding.bestResult.text = "${getString(R.string.best_result)} $newResult%"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}