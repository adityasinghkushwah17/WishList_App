package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.WishRepository
) : ViewModel() {
    var WishTitleState by mutableStateOf("")
    var WishDescriptionState by mutableStateOf("")

    fun onWishtitleChanged(newString: String) {
        WishTitleState = newString
    }

    fun onWishDescChanged(newString: String) {
        WishDescriptionState = newString
    }

    // Use a StateFlow instead of lateinit to ensure it's always initialized
    private val _getAllWishes = MutableStateFlow<List<Wish>>(emptyList())
    val getAllWishes: StateFlow<List<Wish>> = _getAllWishes.asStateFlow()

    init {
        viewModelScope.launch {
            val wishes = wishRepository.getAllWishes()
            wishes.collect { wishList ->
                _getAllWishes.value = wishList
            }
        }
    }

    fun Addwish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.Addwish(wish)
        }
    }

    fun Updatewish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.Updatewish(wish)
        }
    }

    fun Deletewish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.DeleteAwish(wish)
        }
    }
    fun getWishById(id: Long): Flow<Wish?>{
        return wishRepository.getwishbyID(id)
    }
}

