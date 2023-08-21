package com.example.radio_button

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.radio_button.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SecondFragmentArgs by navArgs()
        val selectedOption = args.selectedOption
        val searchText = args.searchText
        val selectedFruits = args.selectedFruits

        // Use the data as needed
        binding.textSelectedOption.text = "Selected Option: $selectedOption"
        binding.textSearchText.text = "Search Text: $searchText"
        binding.textSelectedFruits.text = "Selected Fruits: ${selectedFruits.joinToString(", ")}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

