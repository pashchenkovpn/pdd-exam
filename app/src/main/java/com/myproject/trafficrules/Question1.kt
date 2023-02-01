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
import com.myproject.trafficrules.databinding.FragmentFirstQuestionBinding


class Question1 : Fragment() {

    private var _binding: FragmentFirstQuestionBinding? = null
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
        _binding = FragmentFirstQuestionBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.button.apply { isEnabled = false }

        val image = binding.linearLayoutCompat
        val radioGroup = binding.radioGroup
        val radiobutton1 = binding.radioButton1
        val radiobutton2 = binding.radioButton2
        val radiobutton3 = binding.radioButton3


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

        viewModel.buttonStateLiveData.observe(viewLifecycleOwner) {
            button.isEnabled = it
        }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            viewModel.enableButton()
        }

        button.setOnClickListener {
            val checkedRadioButtonID = radioGroup.checkedRadioButtonId
            Log.d("checked ID", "$checkedRadioButtonID")
            when(checkedRadioButtonID) {
                radiobutton1.id -> view.findNavController().navigate(R.id.action_firstQuestionFragment_to_q1correct)
                radiobutton2.id -> view.findNavController().navigate(R.id.action_firstQuestionFragment_to_q1correct)
                radiobutton3.id -> {
                    viewModel.increaseCorrectAnswers()
                    view.findNavController().navigate(R.id.secondQuestionFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disableButton()
        _binding = null
    }
}