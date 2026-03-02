package com.example.taller1.data

import com.example.taller1.data.remote.RickMortyApi
import com.example.taller1.data.repository.CharacterRepository

object AppContainer {
    val characterRepository: CharacterRepository by lazy {
        CharacterRepository(api = RickMortyApi())
    }
}
