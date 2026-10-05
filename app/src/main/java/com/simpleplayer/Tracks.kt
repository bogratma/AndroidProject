package com.simpleplayer

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND

data class Track(val id: String, val tittle: String)
val tracks = listOf(
    Track("1", "One"),
    Track("2","Two")
)
fun shareTrack(context: Context, trackId: String, trackTittle: String){
    val url = "https://simpleplayer.com/track/$trackId"
    val sendIntent: Intent = Intent().apply {
        action = ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Track: $trackTittle and $url")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent,"Share track"))
}