package com.example.radio_button

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FirstViewModel : ViewModel() {
     var _selectedOption: String? = null
    private var _searchText: String = ""
    private var _selectedFruits: List<String> = emptyList()
    private val _fruits = MutableLiveData<List<String>>()
    val fruits: LiveData<List<String>> = _fruits
//    val fruits = listOf("Apple", "Banana", "Orange", "Grapes", "Mango", "Ball", "Basket")

//    private val _nextButton = MutableLiveData<Boolean>()
//    val nextButton: LiveData<Boolean> = _nextButton


    fun setSelectedOption(option: String) {
        _selectedOption = option
    }

    fun setSearchText(text: String) {
        _searchText = text
    }

    fun setSelectedFruits(fruits: List<String>) {
        _selectedFruits = fruits
    }

    fun clearSelectedFruits() {
        _selectedFruits = emptyList()
    }
    fun callListOfFruits()
    {
        _fruits.value=listOf("Apple", "Banana", "Orange", "Grapes", "Mango", "Ball", "Basket")
    }
}
