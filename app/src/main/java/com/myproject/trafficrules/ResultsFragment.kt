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
import androidx.lifecycle.lifecycleScope
import com.myproject.trafficrules.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
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
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return (binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultsCount = binding.resultCount
        val resultTextView = binding.resultTextView2


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.correctAnswersAmount.collect{
                resultsCount.text = it.toString()
                resultTextView.text = when (resultsCount.text) {
                    "0" -> "Может на метро?"
                    "1" -> "Может на метро?"
                    "3" -> "Повторяй ПДД"
                    "4" -> "Ну почти"
                    else -> "Так держать!"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}