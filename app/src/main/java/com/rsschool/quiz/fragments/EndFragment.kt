package com.rsschool.quiz.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentEndBinding
import com.rsschool.quiz.interfaces.BackButtonVisibilityInterface
import com.rsschool.quiz.interfaces.OnBackPressedFragmentListener
import com.rsschool.quiz.interfaces.SetThemeInterface
import com.rsschool.quiz.interfaces.TitleChangeInterface
import com.rsschool.quiz.preferences.AppPreferences
import com.rsschool.quiz.questions.QuestionsManager

class EndFragment : Fragment(), SetThemeInterface {
    private var _binding: FragmentEndBinding? = null
    private val binding
        get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? BackButtonVisibilityInterface)?.setBackButtonVisibility(false)
        (context as? TitleChangeInterface)?.changeTitle(getString(R.string.result))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setTheme(context, R.style.Theme_Quiz_End)
        _binding = FragmentEndBinding.inflate(inflater, container, false)

        with(binding) {
            val result = QuestionsManager.getResult()
            resultView.text = "${getString(R.string.result)}: $result%"

            AppPreferences(context as Context).setResult(result)

            shareBtn.setOnClickListener { onShareClick() }
            homeBtn.setOnClickListener { onHomeClick() }
            exitBtn.setOnClickListener { onExitClick() }
        }

        return binding.root
    }

    private fun onShareClick() {
        val msg = QuestionsManager.getMessage(context as Context)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        startActivity(Intent.createChooser(intent, getString(R.string.share)))
    }

    private fun onHomeClick() {
        (context as? OnBackPressedFragmentListener)?.onBackPressedFragment()
    }

    private fun onExitClick() {
        activity?.finishAndRemoveTask()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}