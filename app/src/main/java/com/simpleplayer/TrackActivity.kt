package com.simpleplayer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simpleplayer.ui.theme.SimplePlayerTheme
import android.util.Log

class TrackActivity : ComponentActivity() {
    private var currentId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TrackActivity","onCreate")
        currentId = intent.data?.lastPathSegment
        Show(currentId)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("TrackActivity","onNewIntent")
        setIntent(intent)
        currentId = intent.data?.lastPathSegment
        Show(currentId)
    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d("TrackActivity","onSaveInstanceState")
//        outState.putString("track_id",currentId)
//    }
    fun Show(trackId: String?){
        val track = tracks.firstOrNull{it.id==trackId}
        setContent {
            SimplePlayerTheme {
                Column(modifier = Modifier.padding(40.dp)) {
                    if (track != null) {
                        Text(text = "Title ${track.tittle}")
                        Text(text = "ID ${trackId}")
                    } else {
                        Text(text = "Track isn't found")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TrackActivity","onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("TrackActivity","onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("TrackActivity","onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("TrackActivity","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TrackActivity","onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("TrackActivity","onRestart")
    }
}


