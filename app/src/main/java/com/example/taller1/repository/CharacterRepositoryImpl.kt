package com.example.taller1.repository

import com.example.taller1.api.RickMortyApi
import com.example.taller1.model.Character

class CharacterRepositoryImpl(
    private val api: RickMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> {
        return api.getCharacters(page = page).results
    }
}
