package com.example.randomuser.data.model.local

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE email=:email")
    fun getUser(email: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Delete
    suspend fun delete(user: User)


}
