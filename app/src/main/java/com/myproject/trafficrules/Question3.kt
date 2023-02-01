package com.myproject.trafficrules

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.myproject.trafficrules.databinding.FragmentThirdQuestionBinding


class Question3 : Fragment() {


    private var _binding: FragmentThirdQuestionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.addCountToExit()
            FragmentManager.POP_BACK_STACK_INCLUSIVE
            Toast.makeText(context, R.string.exit, Toast.LENGTH_SHORT)
                .show()
            viewModel.exit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdQuestionBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.button.apply { isEnabled = false }

        val image = binding.linearLayoutCompat
        val radioGroup = binding.radioGroup3
        val radiobutton1 = binding.radioButton1
        val radiobutton2 = binding.radioButton2
        val radiobutton3 = binding.radioButton3
        val radiobutton4 = binding.radioButton4

        image.apply {
            alpha = 0f
            isVisible
        }.animate().apply {
            alpha(1f)
            duration = 1000
            start()
        }

        radiobutton1.apply {
            alpha = 0f
            isVisible
        }.animate().apply {
            alpha(1f)
            duration = 1000
            start()
        }

        radiobutton2.apply {
            alpha = 0f
            isVisible
        }.animate().apply {
            alpha(1f)
            duration = 2000
            start()
        }

        radiobutton3.apply {
            alpha = 0f
            isVisible
        }.animate().apply {
            alpha(1f)
            duration = 3000
            start()
        }

        radiobutton4.apply {
            alpha = 0f
            isVisible
        }.animate().apply {
            alpha(1f)
            duration = 4000
            start()
        }

        viewModel.buttonStateLiveData.observe(viewLifecycleOwner) {
            button.isEnabled = it
        }

        binding.radioGroup3.setOnCheckedChangeListener { _, _ ->
            viewModel.enableButton()
        }

        button.setOnClickListener {
            val checkedRadioButtonID = radioGroup.checkedRadioButtonId
            Log.d("checked ID", "$checkedRadioButtonID")
            when (checkedRadioButtonID) {
                radiobutton1.id -> view.findNavController().navigate(R.id.action_thirdQuestionFragment_to_q3correct)
                radiobutton2.id -> {
                    viewModel.increaseCorrectAnswers()
                    view.findNavController().navigate(R.id.action_thirdQuestionFragment_to_question4)
                }
                radiobutton3.id -> view.findNavController().navigate(R.id.action_thirdQuestionFragment_to_q3correct)
                radiobutton4.id -> view.findNavController().navigate(R.id.action_thirdQuestionFragment_to_q3correct)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disableButton()
        _binding = null
    }
}