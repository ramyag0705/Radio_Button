package com.example.radio_button

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.radio_button.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.callListOfFruits()
        viewModel.fruits.observe(viewLifecycleOwner) { fruits ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                fruits
            )
            binding.autoCompleteTextView.apply {
                setAdapter(adapter)

                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        // Not used
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        adapter.filter.filter(s)
                    }

                    override fun afterTextChanged(s: Editable?) {
                        // Not used
                    }
                })

//                setOnItemClickListener { _, _, position, _ ->
//                    val selectedFruit = adapter.getItem(position) as String
//                    setText(selectedFruit)
//                    clearFocus()
//                    // Do something with the selected fruit
//                }
            }


//            viewModel.nextButton.observe(viewLifecycleOwner) { enable ->
//                binding.buttonNext.isEnabled = enable
//            }


            binding.buttonNext.setOnClickListener {
//            var selectedOption = ""
                if (binding.radioButtonYes.isChecked) {
                    viewModel.setSelectedOption("Yes")
//                selectedOption = "Yes"
                } else if (binding.radioButtonNo.isChecked) {
                    viewModel.setSelectedOption("No")
//                selectedOption = "No"
                }


//            val SelectedOption = if (binding.radioButtonYes.isChecked) "Yes" else "No"
                val searchText = binding.autoCompleteTextView.text.toString()

                val selectedFruits = mutableListOf<String>()

                if (binding.checkboxApple.isChecked && fruits.contains("Apple")) {
                    selectedFruits.add("Apple")
                }
                if (binding.checkboxBanana.isChecked && fruits.contains("Banana")) {
                    selectedFruits.add("Banana")
                }
                if (binding.checkboxOrange.isChecked && fruits.contains("Orange")) {
                    selectedFruits.add("Orange")
                }


//            viewModel.SelectedOption(selectedOption)
                viewModel.setSearchText(searchText)
                viewModel.setSelectedFruits(selectedFruits)


                val filteredSearchText =
                    fruits.find { it.equals(searchText, ignoreCase = true) }

                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    viewModel._selectedOption.takeIf { it != null } ?: "",
                    if (selectedFruits.isNotEmpty() || filteredSearchText != null) filteredSearchText
                        ?: "" else "",
                    selectedFruits.toTypedArray()
                )
                println(viewModel._selectedOption)
                findNavController().navigate(action)
            }
        }
    }

        override fun onResume() {
            super.onResume()
            clearFields()
            viewModel.clearSelectedFruits()
        }

        private fun clearFields() {
            binding.autoCompleteTextView.setText("")
            binding.radioGroup.clearCheck()
            binding.checkboxApple.isChecked = false
            binding.checkboxBanana.isChecked = false
            binding.checkboxOrange.isChecked = false
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}



