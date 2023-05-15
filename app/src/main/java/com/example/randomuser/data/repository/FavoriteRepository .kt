package com.example.randomuser.data.repository

import com.example.randomuser.data.model.local.User
import com.example.randomuser.data.model.local.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository  @Inject constructor(
    private val userDao: UserDao
) {
    fun getFavoriteUsers(): List<User> {
        return userDao.getUsers()
    }

    suspend fun addFavoriteUser(favoriteUser: User) {
        userDao.insertUser(favoriteUser)
    }

    suspend fun removeFavoriteUser(favoriteUser: User) {
        userDao.delete(favoriteUser)
    }

}