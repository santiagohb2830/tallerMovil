package com.example.taller1.viewmodel

import com.example.taller1.model.Character

sealed interface CharactersUiState {
    data object Loading : CharactersUiState

    data class Success(
        val characters: List<Character>
    ) : CharactersUiState

    data class Error(
        val message: String
    ) : CharactersUiState
}
