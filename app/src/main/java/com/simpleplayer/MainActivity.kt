package com.simpleplayer


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simpleplayer.ui.theme.SimplePlayerTheme
//git
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimplePlayerTheme {
                LazyColumn(modifier = Modifier.padding(top = 48.dp)) {
                    items(tracks){
                        track -> Text(
                            text = "${track.tittle} - ${track.id}",
                            modifier = Modifier.fillParentMaxWidth()
                                .clickable{
                                    startActivity(
                                        Intent(this@MainActivity, TrackActivity::class.java)
                                            .putExtra("Track ID", track.id)
                                    )
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}




