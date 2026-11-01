package com.simpleplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.simpleplayer.ui.theme.MusicTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SearchScreen() {
    var query by remember {
        mutableStateOf("")
    }
    var state by remember {
        mutableStateOf(SearchState.Content)
    }
    var tracks by remember {
        mutableStateOf(demoTracks)
    }
    val startSearch: () -> Unit = {
        if (
            query.isNotBlank() &&
            state != SearchState.Loading
        ) {
            state = SearchState.Loading
        }
    }
    LaunchedEffect(state) {
        if (state == SearchState.Loading) {
            val request = query.trim()
            delay(1000.milliseconds)
            tracks = demoTracks.filter { track ->
                track.title.contains(
                    request,
                    ignoreCase = true
                ) || track.artist.contains(
                    request,
                    ignoreCase = true
                )
            }
            state = when {
                request.equals(
                    "error",
                    ignoreCase = true
                ) -> SearchState.Error
                tracks.isEmpty() -> SearchState.Empty
                else -> SearchState.Content
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenSizes.gap)
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        Text(
            text = stringResource(R.string.search),
            style = MaterialTheme.typography.headlineSmall
        )
        SearchField(
            query = query,
            onQueryChange = { newText -> query = newText },
            onSearch = startSearch,
            enabled = state != SearchState.Loading
        )
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                SearchState.Loading -> {
                    LoadingComponent(
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
                SearchState.Content -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(ScreenSizes.smallGap)
                    ) {
                        items(tracks) { track ->
                            TrackItem(track = track,
                                onClick = {}
                            )
                        }
                    }
                }
                SearchState.Empty -> {
                    EmptyContent(onReset = {
                            query = ""
                            tracks = demoTracks
                            state = SearchState.Content
                        }
                    )
                }
                SearchState.Error -> {
                    ErrorComponent(
                        onRetry = startSearch
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Шрифт 150%",
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
@Composable
fun SearchScreenPreview() {
    MusicTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchScreen()
        }
    }
}
