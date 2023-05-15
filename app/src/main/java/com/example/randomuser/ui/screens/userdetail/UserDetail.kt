package com.example.randomuser.ui.screens.userdetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.randomuser.R
import com.google.gson.Gson
import com.example.randomuser.ui.component.CircularIndeterminateProgressBar
import com.example.randomuser.data.model.Results
import com.example.randomuser.ui.component.text.SubtitlePrimary
import com.example.randomuser.ui.component.text.SubtitleSecondary
import com.example.randomuser.ui.theme.*
import com.example.randomuser.utils.AppConstant
import com.example.randomuser.utils.network.DataState
import com.example.randomuser.utils.pagingLoadingState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import timber.log.Timber

@Composable
fun UserDetail(navController: NavController, userId: String?) {
    val userDetailViewModel = hiltViewModel<UserDetailViewModel>()
    val progressBar = remember { mutableStateOf(false) }
    val user = Gson().fromJson(AppConstant.mockvalue, Results::class.java)
    val userDetail = userDetailViewModel.userDetail
    Timber.v(userId)

    LaunchedEffect(true) {
        userDetailViewModel.userDetailApi(userId)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DefaultBackgroundColor
            )
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)
        userDetail.value?.let { it ->
            if (it is DataState.Success<Results>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        imageModel = { it.data.picture?.large },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            contentDescription = "user Detail",
                            colorFilter = null,
                        ),
                        component = rememberImageComponent {
                            +CircularRevealPlugin(
                                duration = 800
                            )
                        },
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "${it.data.name?.title} ${it.data.name?.first} ${it.data.name?.last}  ",
                            modifier = Modifier.padding(top = 10.dp),
                            color = FontColor,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Column(Modifier.weight(1f)) {
                            it.data.email?.let { it1 ->
                                SubtitlePrimary(
                                    text = it1,
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.email)
                                )
                            }

                        }
                        Column(Modifier.weight(1f)) {
                            it.data.phone?.let { it1 ->
                                SubtitlePrimary(
                                    text = it1,
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.phone)
                                )
                            }
                        }
                        Column(Modifier.weight(1f)) {
                            it.data.dob?.date?.let { it1 ->
                                SubtitlePrimary(
                                    text = it1
                                )
                                SubtitleSecondary(
                                    text = stringResource(R.string.dob)
                                )
                            }
                        }


                    }
                }
                // }
            }
        }
    }
}


