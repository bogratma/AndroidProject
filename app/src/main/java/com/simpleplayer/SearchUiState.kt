package com.simpleplayer

sealed interface SearchResult {
    data object Loading : SearchResult

    data class Content(
        val tracks: List<Track>
    ) : SearchResult

    data object Empty : SearchResult

    data object Error : SearchResult
}

data class SearchUiState(
    val query: String = "",
    val result: SearchResult = SearchResult.Content(demoTracks),
    val message: String? = null
)
