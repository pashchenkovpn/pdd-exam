package com.myproject.trafficrules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.myproject.trafficrules.databinding.FragmentQ2correctBinding


class Q2correct : Fragment() {

    private var _binding : FragmentQ2correctBinding? = null
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
        _binding = FragmentQ2correctBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.nextButton2
        button.setOnClickListener{
            view.findNavController().navigate(R.id.action_q2correct_to_thirdQuestionFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}