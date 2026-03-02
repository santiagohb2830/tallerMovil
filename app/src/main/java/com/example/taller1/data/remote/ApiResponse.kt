package com.example.taller1.data.remote

import com.example.taller1.model.Character
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val results: List<Character> = emptyList()
)
