package com.example.randomuser.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.randomuser.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {
    fun usersByMail(email: String?) =
        repo.userPagingDataSource(email).cachedIn(viewModelScope)
}