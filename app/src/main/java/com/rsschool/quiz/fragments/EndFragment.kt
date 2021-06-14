package com.rsschool.quiz.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentEndBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityInterface
import com.rsschool.quiz.interfaces.TitleChangeInterface

class EndFragment : Fragment() {
    private var _binding: FragmentEndBinding? = null
    private val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? BackButtonVisibilityInterface)?.setBackButtonVisibility(false)
        (context as? TitleChangeInterface)?.changeTitle(getString(R.string.result))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}