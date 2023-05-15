package com.example.randomuser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.randomuser.R
import com.example.randomuser.navigation.AppDestinations.CAT_DETAIL_ID_KEY
import com.example.randomuser.ui.nowplaying.AllUsers
import com.example.randomuser.ui.screens.userdetail.UserDetail


private object AppDestinations {
    const val CAT_DETAIL_ROUTE = "userDetail"
    const val CAT_DETAIL_ID_KEY = "userId"
}

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier,
) {
    val actions = remember(navController) { AppActions(navController) }
    NavHost(navController, startDestination = Screen.Home.route, modifier) {
       composable(Screen.Home.route) {
            AllUsers(
                navController = navController,
               actions.selectedCat
            )
        }

        /////////////totest ///////////////////////
       /* composable(
            Screen.UserDetail.route.plus(Screen.UserDetail.objectPath),
            arguments = listOf(navArgument(Screen.UserDetail.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.user_detail)
            val userGson = it.arguments?.getString(Screen.UserDetail.objectName)
            userGson?.let {
                UserDetail(
                    navController = navController, userGson
                )
            }
        }*/

        /*composable(
            Screen.UserDetail.route.plus(Screen.UserDetail.objectPath),
            arguments = listOf(navArgument(Screen.UserDetail.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val userInfo = it.arguments?.getString(Screen.UserDetail.objectName)
            userInfo?.let {
                UserDetail(
                    navController = navController, userInfo
                )
            }
        }*/

      /*  composable(
            Screen.Home.route
        ) {
          //  CatsList(selectedCat = actions.selectedCat)
            AllUsers(
                navController = navController,
                actions.selectedCat
            )
        }*/

        composable(
            "${AppDestinations.CAT_DETAIL_ROUTE}/{$CAT_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(CAT_DETAIL_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            UserDetail(
                navController = navController,
                userId = arguments.getString(CAT_DETAIL_ID_KEY)
            )
        }

    }
}

private class AppActions(
    navController: NavHostController
) {
    val selectedCat: (String) -> Unit = { catId: String ->
        navController.navigate("${AppDestinations.CAT_DETAIL_ROUTE}/$catId")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
      //  Screen.MovieDetail.route -> stringResource(id = R.string.movie_detail)
        Screen.UserDetail.route -> stringResource(id = R.string.movie_detail)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
