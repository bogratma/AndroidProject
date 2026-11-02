package com.simpleplayer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SearchViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState(query = savedStateHandle.get<String>("query")?:""))
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    fun onQueryChange(newQuery: String){
            savedStateHandle["query"] = newQuery
           _uiState.update { currentState ->currentState.copy(query = newQuery) }
    }
    fun search(){
      val request = _uiState.value.query.trim()
      if(request.isBlank() || _uiState.value.state == SearchState.Loading) { return }
        _uiState.update { currentState -> currentState.copy(state = SearchState.Loading) }
        viewModelScope.launch {
            delay(1000.milliseconds)
            val foundTracks = demoTracks.filter {
                track -> track.title.contains(request, ignoreCase = true)
                    || track.artist.contains(request, ignoreCase = true)
            }
            val newState = when {
                request.equals("error", ignoreCase = true) -> SearchState.Error
                foundTracks.isEmpty() -> SearchState.Empty
                else -> SearchState.Content
            }
            _uiState.update { currentState -> currentState.copy(state=newState, tracks = foundTracks) }
        }
    }
    fun resetSearch(){
        savedStateHandle["query"]=""
        _uiState.update {currentState -> currentState.copy(query = "", state = SearchState.Content,
            tracks = demoTracks)}

    }
    fun onPlayClick(track: Track){
        _uiState.update {currentState -> currentState.copy(message = "Play: ${track.title}")}
    }
    fun onMessageShow(){
        _uiState.update { currentState -> currentState.copy(message = null) }
    }
}