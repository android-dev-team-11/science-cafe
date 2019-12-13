package com.example.models

import com.google.gson.annotations.SerializedName

data class Progress(
    @SerializedName("bar")
    val bar:Int,

    @SerializedName("percentage")
    val percentage:Int,

    @SerializedName("reward")
    val reward:Reward,

    @SerializedName("attendedEvents")
    val attendedEvents:Set<Event>

)