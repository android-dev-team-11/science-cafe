package com.example.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginRequest(

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
)