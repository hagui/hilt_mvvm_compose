package com.example.randomuser.ui.nowplaying


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.randomuser.data.model.Results
import com.example.randomuser.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(val repo: UserRepository) : ViewModel() {
    var selectedGenre: MutableState<Results> =
        mutableStateOf(Results())
    val filterData = MutableStateFlow<Results?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val fetchUsers = filterData.flatMapLatest {
        repo.userPagingDataSource(it?.email)
    }.cachedIn(viewModelScope)

}