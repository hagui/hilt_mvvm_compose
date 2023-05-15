package com.example.randomuser.data.model

import com.google.gson.annotations.SerializedName


data class Info (

  @SerializedName("seed"    ) var seed    : String? = null,
  @SerializedName("results" ) var results : Int?    = null,
  @SerializedName("page"    ) var page    : Int    = 0,
  @SerializedName("version" ) var version : String? = null

)