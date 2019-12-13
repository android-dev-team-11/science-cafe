package com.example.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class News(
    @SerializedName("id")
    val id:Int,

    @SerializedName("title")
    val title: String ,

    @SerializedName("content")
    val content: String,

    @SerializedName("postedDate")
    val postedDate: Date,

    @SerializedName("author")
    val author: String,

    @SerializedName("imageUrl")
    val imageUrl: String
)