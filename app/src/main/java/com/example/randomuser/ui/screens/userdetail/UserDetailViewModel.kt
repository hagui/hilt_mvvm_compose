package com.example.randomuser.ui.screens.userdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.data.model.BaseModel
import com.example.randomuser.data.model.Results
import com.example.randomuser.data.repository.UserRepository
import com.example.randomuser.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {


    val userDetail: MutableState<DataState<Results>?> = mutableStateOf(null)


    fun userDetailApi(userId: String?) {
        viewModelScope.launch {
            repo.userDetail(userId).onEach{
                userDetail.value = it
            }.launchIn(viewModelScope)
        }

    }
}