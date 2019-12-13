package com.example.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id:Int = 0,

    @SerializedName("enabled")
    var enabled:Int = 1,

    @SerializedName("token")
    var token:String = "",

    @SerializedName("username")
    var username:String = "",

    @SerializedName("password")
    var password:String = "",

    @SerializedName("firstName")
    var firstName: String = "",

    @SerializedName("lastName")
    var lastName: String = "",

    @SerializedName("roles")
    var roles: Set<Role> =  emptySet(),

    @SerializedName("position")
    var position:String = "",

    @SerializedName("unit")
    var unit:String = "",

    @SerializedName("programs")
    var programs:Set<Program> =  emptySet(),

    @SerializedName("title")
    var title:String = "",

    @SerializedName("email")
    var email:String = ""

)