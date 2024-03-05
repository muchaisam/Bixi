package com.example.bixi.models

//public, no-argument constructor.
data class LiveStream(
    val streamTitle: String = "",
    val streamerName: String = "",
    val thumbnailUrl: String = ""
    // Add other properties here with default values
)