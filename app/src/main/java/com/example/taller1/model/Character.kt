package com.example.taller1.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: OriginRef,
    val location: LocationRef
)

@Serializable
data class OriginRef(
    val name: String,
    val url: String
)

@Serializable
data class LocationRef(
    val name: String,
    val url: String
)
