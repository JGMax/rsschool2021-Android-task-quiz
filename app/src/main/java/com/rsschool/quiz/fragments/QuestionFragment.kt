package com.rsschool.quiz.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rsschool.quiz.QuestionFragmentArgs
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityInterface

class QuestionFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val args: QuestionFragmentArgs by navArgs()
        Log.e("args", args.numberOfQuestion.toString())
        (context as? BackButtonVisibilityInterface)?.setBackButtonVisibility(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: QuestionFragmentArgs by navArgs()
        Log.e("argCreated", args.numberOfQuestion.toString())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}