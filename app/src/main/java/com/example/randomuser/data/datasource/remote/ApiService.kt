package com.example.randomuser.data.datasource.remote

import com.example.randomuser.data.model.Results
import com.example.randomuser.data.model.UsersSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun searchUsers(
        @Query("seed") seed: String,
        @Query("page") page: Int,
        @Query("results") results: Int,
    ): UsersSearchResponse

    @GET("api")
    suspend fun userDetail(
        @Query("seed") seed: String,
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("email") userId: String):Results

}