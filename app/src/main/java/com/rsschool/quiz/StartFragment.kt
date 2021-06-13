package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.databinding.FragmentStartBinding
import com.rsschool.quiz.preferences.AppPreferences

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = _binding!!

    private val preferences by lazy { AppPreferences(context as Context) }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        with(binding) {
            updatePreviousResultView(preferences.getPreviousResult())
            startBtn.setOnClickListener { onStartClick() }
            resetBtn.setOnClickListener { onResetClick() }
            exitBtn.setOnClickListener {  }
        }
        (context as? BackButtonVisibilityInterface)?.setBackButtonVisibility(false)
        return binding.root
    }

    private fun onStartClick() {
        val action = StartFragmentDirections.actionToQuestion(0)
        findNavController().navigate(action)
    }

    private fun onResetClick() {
        preferences.setPreviousResult(0.0f)
        updatePreviousResultView(0.0f)
    }

    @SuppressLint("SetTextI18n")
    private fun updatePreviousResultView(newResult: Float) {
        binding.previousResult.text = "${getString(R.string.previous_result)} $newResult%"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}