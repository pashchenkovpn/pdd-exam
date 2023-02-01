package com.myproject.trafficrules

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.myproject.trafficrules.databinding.FragmentIntroBinding



class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            view.findNavController().navigate(R.id.firstQuestionFragment)
        }

        ObjectAnimator.ofFloat(binding.startButton, View.ROTATION, 0f, 9f, -9f, 0f).apply {
            duration = 1800
            repeatCount = 0
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}