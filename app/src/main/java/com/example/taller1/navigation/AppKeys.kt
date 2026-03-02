package com.example.taller1.navigation

import com.example.taller1.model.Character
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListKey

@Serializable
data class CharacterDetailKey(val character: Character)
