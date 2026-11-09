package com.simpleplayer

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.simpleplayer.ui.theme.ScreenSizes

@Composable
fun TrackDetailScreen(
    track: Track?,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val shareTitle = stringResource(R.string.share_track)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(ScreenSizes.gap),
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        TextButton(onClick = onBack) {
            Text("Назад")
        }

        if (track == null) {
            Text(
                text = "Трек не найден",
                style = MaterialTheme.typography.headlineSmall
            )
        } else {
            Text(
                text = track.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = track.artist,
                style = MaterialTheme.typography.bodyLarge
            )

            Button(onClick = onEdit) {
                Text("Редактировать")
            }

            Button(
                onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "https://simpleplayer.com/track/${track.id}"
                        )
                    }

                    context.startActivity(
                        Intent.createChooser(shareIntent, shareTitle)
                    )
                }
            ) {
                Text(stringResource(R.string.share))
            }
        }
    }
}