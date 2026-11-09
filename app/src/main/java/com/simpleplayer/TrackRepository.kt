package com.simpleplayer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
object TrackRepository {
    private val _tracks = MutableStateFlow(demoTracks)

    val tracks = _tracks.asStateFlow()

    fun getTrackById(id: Int): Track? {
        return _tracks.value.find { track ->
            track.id == id
        }
    }
    fun updateTrack(updatedTrack: Track) {
        _tracks.update { currentTracks ->
            currentTracks.map { track ->
                if (track.id == updatedTrack.id) {
                    updatedTrack
                } else {
                    track
                }
            }
        }
    }
}
