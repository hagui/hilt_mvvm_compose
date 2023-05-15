package com.example.randomuser.ui.screens.user

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.randomuser.navigation.Screen
import com.example.randomuser.ui.component.UserItemList

@Composable
fun UsersScreen(
    navController: NavController,
    email: String?,
    selectedUser: (String) -> Unit
) {
    val usersViewModel = hiltViewModel<UsersViewModel>()
    UserItemList(
        navController,
        usersViewModel.usersByMail(email),
        selectedUser,
    ) { /*userGson ->
        navController.navigate(Screen.UserDetail.route.plus("/$userGson"))*/
    }
}