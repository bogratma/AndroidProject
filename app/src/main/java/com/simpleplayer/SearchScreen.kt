package com.simpleplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.simpleplayer.ui.theme.MusicTheme
import com.simpleplayer.ui.theme.ScreenSizes

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onReset: () -> Unit,
    onPlayClick: (Track) -> Unit
) {
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
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            enabled = uiState.result != SearchResult.Loading
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (val result = uiState.result) {
                SearchResult.Loading -> {
                    LoadingComponent(
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }

                is SearchResult.Content -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(ScreenSizes.smallGap)
                    ) {
                        items(result.tracks) { track ->
                            TrackItem(
                                track = track,
                                onClick = { onPlayClick(track) }
                            )
                        }
                    }
                }

                SearchResult.Empty -> {
                    EmptyContent(onReset = onReset)
                }

                SearchResult.Error -> {
                    ErrorComponent(onRetry = onSearch)
                }
            }
        }
    }
}

@Composable
private fun SearchPreviewContent(uiState: SearchUiState) {
    MusicTheme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            SearchScreen(
                uiState = uiState,
                onQueryChange = {},
                onSearch = {},
                onReset = {},
                onPlayClick = {}
            )
        }
    }
}

@Preview(
    name = "Content",
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
@Composable
fun SearchContentPreview() {
    SearchPreviewContent(
        uiState = SearchUiState(
            query = "Track",
            result = SearchResult.Content(demoTracks)
        )
    )
}

@Preview(
    name = "Empty",
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
@Composable
fun SearchEmptyPreview() {
    SearchPreviewContent(
        uiState = SearchUiState(
            query = "фвфвфвфы",
            result = SearchResult.Empty
        )
    )
}

@Preview(
    name = "Error",
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
@Composable
fun SearchErrorPreview() {
    SearchPreviewContent(
        uiState = SearchUiState(
            query = "error",
            result = SearchResult.Error
        )
    )
}

@Preview(
    name = "Loading",
    widthDp = 360,
    heightDp = 800,
    fontScale = 1.5f
)
@Composable
fun SearchLoadingPreview() {
    SearchPreviewContent(
        uiState = SearchUiState(
            query = "Track",
            result = SearchResult.Loading
        )
    )
}
