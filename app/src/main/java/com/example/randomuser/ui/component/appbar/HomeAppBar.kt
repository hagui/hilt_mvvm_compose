package com.example.randomuser.ui.component.appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.randomuser.ui.theme.Purple500

@Composable
fun HomeAppBar(title: String, openDrawer: () -> Unit, openFilters: () -> Unit) {
    TopAppBar(
        backgroundColor = Purple500,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        },
        // open close drawer
       /* navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(Icons.Default.Menu, "Menu")
            }
        },*/
        /*actions = {
            IconButton(onClick = openFilters) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }*/
    )
}
