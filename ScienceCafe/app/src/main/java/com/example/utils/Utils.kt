package com.example.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import android.annotation.SuppressLint
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(date: Date): String {
    return SimpleDateFormat("yyyy/dd/MM")
        .format(date).toString()
}

fun convertLongToTimeString(date: Date): String {
    return SimpleDateFormat("hh:mm:ss")
        .format(date).toString()
}