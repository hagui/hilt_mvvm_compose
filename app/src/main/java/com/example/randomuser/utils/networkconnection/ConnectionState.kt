package com.example.randomuser.utils.networkconnection

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}