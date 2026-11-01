package com.simpleplayer
enum class SearchState {
    Loading,
    Content,
    Empty,
    Error
}
data class Track(
    val id:Int,
    val title:String,
    val artist: String
)
val demoTracks = listOf(
    Track(1,"Track 1", "Vlad"),
    Track(2,"Track 2", "Vlad"),
    Track(3,"Track 3", "Vlad"),
    Track(4,"Track 4", "Vlad"),
    Track(5,"Track 5", "Vlad")
)