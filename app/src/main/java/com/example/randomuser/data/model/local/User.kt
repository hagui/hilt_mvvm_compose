package com.example.randomuser.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "email") val email: String,
    val name: String,
    val phone: String,
    val dabDate: String,
    val pictureThumb: String,
    val pictureLarge: String
) {

    companion object {

        fun mock() = User(
            email = "attay@gmail.com",
            name = "Frozen II",
            phone = "2019555555",
            dabDate = "1855888",
            pictureLarge = "https://user-images.githubusercontent.com/24237865/75087936-5c1d9f80-553e-11ea-81d3-a912634dd8f7.jpg",
            pictureThumb = "https://media2.giphy.com/media/aQYR1p8saOQla/giphy.gif?cid=ecf05e4701sln9u63lr3z17lh5f3n3h3owrk54zh1183hqmi&rid=giphy.gif&ct=g"
        )
    }
}
