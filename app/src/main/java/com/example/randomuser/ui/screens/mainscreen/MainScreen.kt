package com.example.randomuser.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.randomuser.R
import com.example.randomuser.navigation.Navigation
import com.example.randomuser.navigation.Screen
import com.example.randomuser.navigation.currentRoute
import com.example.randomuser.navigation.navigationTitle
import com.example.randomuser.ui.component.CircularIndeterminateProgressBar
import com.example.randomuser.ui.component.appbar.AppBarWithArrow
import com.example.randomuser.ui.component.appbar.HomeAppBar
import com.example.randomuser.ui.component.appbar.SearchBar
import com.example.randomuser.utils.networkconnection.ConnectionState
import com.example.randomuser.utils.networkconnection.connectivityState
import com.example.randomuser.utils.pagingLoadingState
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val searchProgressBar = remember { mutableStateOf(false) }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // genre api call for first time
   /* LaunchedEffect(key1 = 0) {
        mainViewModel.genreList()
    }
*/
    Scaffold(scaffoldState = scaffoldState, topBar = {
        when (currentRoute(navController)) {
            Screen.Home.route -> {
                if (isAppBarVisible.value) {
                    val appTitle: String = stringResource(R.string.app_title)
                    HomeAppBar(title = appTitle, openDrawer = {
                        scope.launch {
                            //cont forget to save the stat of naVdrawer
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }, openFilters = {
                        isAppBarVisible.value = false
                    })
                } else {
                    SearchBar(isAppBarVisible, mainViewModel)
                }
            }
            else -> {
                AppBarWithArrow(navigationTitle(navController)) {
                    navController.popBackStack()
                }
            }
        }
    }, bottomBar = {
        when (currentRoute(navController)) {
            Screen.Home.route-> {
              //  BottomNavigationUI(navController)
            }
        }
    },snackbarHost = {
        if (isConnected.not()) {
            Snackbar(
                action = {}, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = stringResource(R.string.there_is_no_internet))
            }
        }
    }) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Navigation(navController, Modifier.padding(it))
            Column {
                CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, 0.1f)
                if (isAppBarVisible.value.not()) {
                    SearchUI {
                        isAppBarVisible.value = true
                    }
                }
            }
        }
        mainViewModel.searchData.pagingLoadingState {
            searchProgressBar.value = it
        }
    }
}

@Composable
fun SearchUI(function: () -> Unit) {

}


@Composable
fun BottomNavigationUI(navController: NavController) {
    BottomNavigation {
        val items = listOf(
            Screen.HomeNav,
        )
        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                icon = item.navIcon,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}