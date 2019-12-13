package com.example.models

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

data class Reward(
    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("providerName")
    val providerName: String,

    @SerializedName("startDate")
    val startDate: Date,

    @SerializedName("endDate")
    val endDate: Date,

    @SerializedName("startTime")
    val startTime: String,

    @SerializedName("criteria")
    val criteria: Int,

    @SerializedName("status")
    val status: Int,

    @SerializedName("tags")
    val tags: Set<Tag>,

    @SerializedName("submitter")
    val submitter:User

)