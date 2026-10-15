package com.simpleplayer

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND

data class Track(val id:String, val tittle:String)
val tracks = listOf(
    Track("1","One"),
    Track("2","Two"),
    Track("3","Three")
)
