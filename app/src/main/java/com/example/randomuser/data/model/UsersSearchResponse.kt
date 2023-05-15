package com.example.randomuser.data.model

import com.google.gson.annotations.SerializedName


data class UsersSearchResponse(
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()
)