package com.simpleplayer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.simpleplayer.ui.theme.MusicTheme

class MainActivity : ComponentActivity() {
    private var pendingTrackId by mutableStateOf<Int?>(null)
    private fun getTrackId(intent: Intent): Int? {
        val uri = intent.data ?: return null

        if (
            intent.action != Intent.ACTION_VIEW ||
            uri.scheme != "https" ||
            uri.host != "simpleplayer.com"
        ) {
            return null
        }

        val segments = uri.pathSegments

        return if (segments.size == 2 && segments[0] == "track") {
            segments[1].toIntOrNull()
        } else {
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            pendingTrackId = getTrackId(intent)
        }
        enableEdgeToEdge()
        setContent {
            MusicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        deepLinkTrackId = pendingTrackId,
                        onDeepLinkHandled = {
                            pendingTrackId = null
                        }
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        pendingTrackId = getTrackId(intent)
    }
}
