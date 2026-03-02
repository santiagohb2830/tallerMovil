package com.example.taller1.repository

import com.example.taller1.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<Character>
}
