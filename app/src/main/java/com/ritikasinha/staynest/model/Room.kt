package com.ritikasinha.staynest.model

data class Room(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Double,
    val beds: String,
    val image: String
)