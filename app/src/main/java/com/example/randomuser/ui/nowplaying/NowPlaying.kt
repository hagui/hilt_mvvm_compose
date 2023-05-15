package com.example.randomuser.ui.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.randomuser.data.model.Results
import com.example.randomuser.ui.component.UserItemList

@Composable
fun AllUsers(
    navController: NavController,
    selectedUser: (String) -> Unit,
) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    UserItemList(
        navController = navController,
        users = nowPlayViewModel.fetchUsers,
        selectedUser
    ){
        nowPlayViewModel.filterData.value = Results(it?.id.toString())
        it?.let {
            nowPlayViewModel.selectedGenre.value = it
        }
    }
}