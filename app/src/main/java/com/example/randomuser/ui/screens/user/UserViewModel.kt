package com.example.randomuser.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.data.model.local.User
import com.example.randomuser.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: FavoriteRepository
) : ViewModel() {
    //other methods
    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.removeFavoriteUser(user)
        }
    }

    fun insertFavoriteUser(user: User) {
        viewModelScope.launch {
            userRepository.addFavoriteUser(user)
        }
    }
}
