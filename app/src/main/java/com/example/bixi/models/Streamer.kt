package com.example.bixi.models

//no-argument constructor is required for Firestore
data class Streamer(
    val name: String = "",
    val viewers: Int = 0,
    val tags: List<String> = emptyList(),
    val streamerIcon: String = "" // This can be a drawable resource ID for the streamer's image
)