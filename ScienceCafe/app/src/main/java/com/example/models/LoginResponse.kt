package com.example.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginResponse(

    @SerializedName("jwt")
    val jwt: String

)