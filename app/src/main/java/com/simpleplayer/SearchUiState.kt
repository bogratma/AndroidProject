package com.simpleplayer

data class SearchUiState(
    val query: String="",
    val state: SearchState= SearchState.Content,
    val tracks: List<Track> = demoTracks,
    val message: String? = null
)
