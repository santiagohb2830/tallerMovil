package com.example.taller1.data

import com.example.taller1.api.RickMortyApi
import com.example.taller1.repository.CharacterRepository
import com.example.taller1.repository.CharacterRepositoryImpl

object AppContainer {
    val characterRepository: CharacterRepository by lazy {
        CharacterRepositoryImpl(
            api = RickMortyApi()
        )
    }
}
