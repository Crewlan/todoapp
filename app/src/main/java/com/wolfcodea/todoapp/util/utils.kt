package com.wolfcodea.todoapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String{
    val date = Date(time)
    val formart =  SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())

    return formart.format(date)
}