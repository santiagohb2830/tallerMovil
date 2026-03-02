package com.example.taller1.data.repository

import com.example.taller1.data.remote.RickMortyApi
import com.example.taller1.model.Character

class CharacterRepository(
    private val api: RickMortyApi
) {
    suspend fun getCharacters(page: Int): List<Character> {
        return api.getCharacters(page = page).results
    }
}
