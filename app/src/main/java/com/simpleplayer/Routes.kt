package com.simpleplayer

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object TrackList: NavKey

@Serializable
data class TrackDetail(
    val trackId : Int
) : NavKey

@Serializable
data class TrackEdit(
    val trackId: Int
) : NavKey