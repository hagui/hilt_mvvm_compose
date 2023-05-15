package com.example.randomuser.ui.component

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.randomuser.data.model.Results
import com.example.randomuser.navigation.Screen
import com.example.randomuser.navigation.currentRoute
import com.example.randomuser.ui.theme.DefaultBackgroundColor
import com.example.randomuser.ui.theme.cornerRadius
import com.example.randomuser.utils.AppConstant
import com.example.randomuser.utils.conditional
import com.example.randomuser.utils.items
import com.example.randomuser.utils.pagingLoadingState
import com.google.gson.Gson
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow

@Composable
fun UserItemList(
    navController: NavController,
    users: Flow<PagingData<Results>>,
    selectedUser: (String) -> Unit,
    onclick: (genreId: Results?) -> Unit
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val usersItems: LazyPagingItems<Results> = users.collectAsLazyPagingItems()


    BackHandler(enabled = (currentRoute(navController) == Screen.Home.route)) {
        openDialog.value = true
    }
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        users?.let {
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 9.dp, end = 9.dp)
                    .fillMaxWidth()
            ) {
                /*     *//*items(users) { item ->
                    SelectableGenreChip(
                        selected = item.name === selectedName?.name,
                        genre = item.name,
                        onclick = {
                            onclick(item)
                        }
                    )
                }*/
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .conditional(true) {
                    padding(top = 8.dp)
                },
            content = {
                items(usersItems) { item ->
                    item?.let {
                        UserItemView(item, navController, selectedUser)
                    }
                }
            })
    }
    if (openDialog.value) {
        ExitAlertDialog(navController, {
            openDialog.value = it
        }, {
            activity?.finish()
        })

    }
    usersItems.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun UserItemView(item: Results, navController: NavController, selectedUser: (String) -> Unit) {
    Card(
        elevation = 2.dp, //dimensionResource(id = R.dimen.card_elevation),
        shape = MaterialTheme.shapes.card,
        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)
            .background(Color.White)
    ) {

        Column(Modifier.fillMaxWidth()
            .background(Color.LightGray)) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .padding(0.dp,0.dp,0.dp,0.dp)
                    .size(32.dp),
                //check if                   we need to put it TOP end
                //    .align(Alignment.TopEnd),
                color = Color.Transparent
            ) {
                FavoriteButton(modifier = Modifier.padding(8.dp))
            }
            CoilImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
                    .cornerRadius(10)
                    .align(CenterHorizontally)
                    .clickable {
                        item.email?.let { selectedUser(it) }
                        //   navController.navigate(Screen.UserDetail.route.plus(Gson().toJson(item)))
                        /* val moshi = Moshi.Builder()
                             .add(KotlinJsonAdapterFactory()).build()
                         val jsonAdapter = moshi.adapter(Results::class.java).lenient()
                         val userJson = jsonAdapter.toJson(item)
                         selectedUser(AppConstant.mockvalue)*/
                    },
                imageModel = { item.picture!!.thumbnail },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center,
                    contentDescription = "User item",
                    colorFilter = null,
                ),
                component = rememberImageComponent {
                    +CircularRevealPlugin(
                        duration = 800
                    )
                },
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom

            ){
                Text(
                    text = "${item.name?.first} ${item.name?.last}",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 0.dp)
                        .align(CenterHorizontally)
                        .background(item.gender?.let { if(it == "male") Color.Yellow else Color.Green } ?: Color.Yellow )
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }

        }
    }
/*
    Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)) {
        CoilImage(
            modifier = Modifier
                .size(250.dp)
                .cornerRadius(10)
                .clickable {
                    //navController.navigate(Screen.MovieDetail.route.plus("/${item.email}"))
                },
            imageModel = { item.picture!!.large },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Movie item",
                colorFilter = null,
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = 800
                )
            },
        )
    }
*/
}

