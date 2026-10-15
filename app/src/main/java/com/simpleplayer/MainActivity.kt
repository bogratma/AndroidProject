package com.simpleplayer
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.util.Log
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","onCreate")
        enableEdgeToEdge()
        setContent {
            SimplePlayerTheme() {
                LazyColumn(modifier = Modifier.padding(40.dp)){
                    items(tracks){
                        track -> Text(
                            text = "${track.tittle} - ${track.id}",
                            modifier = Modifier.clickable{
                                val url = "https://simpleplayer.com/track/${track.id}"
                                val i = Intent().apply {
                                    action = ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Track: ${url}")
                                    type="text/plain"
                                }
                                startActivity(i)
                            }
                        )
                    }
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity","onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("MainActivity","onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity","onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("MainActivity","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity","onRestart")
    }
}



