package com.simpleplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simpleplayer.ui.theme.SimplePlayerTheme

class TrackActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trackId = intent.data?.pathSegments?.get(1) ?: intent.getStringExtra("Track ID")
        val track = tracks.first{it.id == trackId}
        setContent {
            SimplePlayerTheme {
                Column(modifier = Modifier.padding(top =64.dp, start = 16.dp)) {
                    Text(track.tittle)
                    Text(track.id)
                    Button(onClick = {shareTrack(this@TrackActivity,track.id,track.tittle)}){
                        Text("Share")
                    }
                }
            }
        }
    }
}