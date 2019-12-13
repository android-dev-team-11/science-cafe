package com.example.models


import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

data class Event(
    @SerializedName("id")
    val id:Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("eventDate")
    val eventDate: Date,

    @SerializedName("startTime")
    val startTime: String,

    @SerializedName("endTime")
    val endTime: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("tags")
    val tags: Set<Tag>,

    @SerializedName("organizer")
    val organizer:User,

    @SerializedName("attendees")
    val attendees:Set<User>,

    @SerializedName("imageUrl")
    val imageUrl: String

)