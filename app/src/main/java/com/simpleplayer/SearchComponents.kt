package com.simpleplayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simpleplayer.ui.theme.MusicTheme

object ScreenSizes {
    val gap = 16.dp
    val smallGap = 8.dp
    val touch = 48.dp
    val cover = 48.dp
    val statusCircle = 88.dp
    val statusIcon = 32.dp
    val errorBadge = 36.dp
    val skeletonLine = 12.dp
}
@Composable
fun TrackItem(
    track: Track,
    onClick: () -> Unit
) {
    Surface {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(ScreenSizes.smallGap),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
        ) {
            Box(
                modifier = Modifier.size(ScreenSizes.cover)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small
                    )
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = track.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            FilledIconButton(
                onClick = onClick,
                modifier = Modifier.size(ScreenSizes.touch)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_play_arrow),
                    contentDescription = stringResource(R.string.play_track,
                        track.title
                    )
                )
            }
        }
    }
}
@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    enabled: Boolean = true
) {

   val description = stringResource(R.string.search_hint)
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth()
            .semantics(){
                contentDescription=description
            },
        singleLine = true,
        placeholder = {
            Text(description)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(
                    R.drawable.ic_search
                ),
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (query.isNotBlank()) {
                    onSearch()
                }
            }
        ),
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor =
                MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor =
                MaterialTheme.colorScheme.surfaceContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        enabled=enabled
    )
}
@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
fun TrackItemPreview() {
    MusicTheme {
        TrackItem(
            track = Track(
                id = 1,
                title = "Track 1",
                artist = "Vlad"
            ),
            onClick = {}
        )
    }
}
@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
fun SearchFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }
    MusicTheme {
        Column {
            SearchField(
                query = text,
                onQueryChange = { newText ->
                    text = newText
                },
                onSearch = {
                    result = "Ищем: $text"
                }
            )
            Text(result)
        }
    }
}