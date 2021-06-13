package com.rsschool.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BackButtonVisibilityInterface, OnBackPressedFragmentListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)

            val controller = findNavController(R.id.container)

            setSupportActionBar(toolbarInclude.toolbar)
            setBackButtonVisibility(false)
            toolbarInclude.toolbar.setNavigationOnClickListener {
                Log.e("ads", controller.backStack.toString())
                controller.popBackStack()
            }
        }
    }

    override fun setBackButtonVisibility(visibility: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }

    override fun onBackPressedFragment() {
        onBackPressed()
    }
}