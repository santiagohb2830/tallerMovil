package com.example.taller1.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taller1.data.AppContainer
import com.example.taller1.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharacterRepository,
    private val initialPage: Int = 2
) : ViewModel() {

    private val _uiState = mutableStateOf<CharactersUiState>(CharactersUiState.Loading)
    val uiState: State<CharactersUiState> = _uiState

    init {
        loadCharacters()
    }

    fun loadCharacters(page: Int = initialPage) {
        _uiState.value = CharactersUiState.Loading

        viewModelScope.launch {
            _uiState.value = try {
                CharactersUiState.Success(
                    characters = repository.getCharacters(page = page)
                )
            } catch (exception: Exception) {
                CharactersUiState.Error(
                    message = exception.message ?: "No se pudieron cargar los personajes."
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CharactersViewModel(
                    repository = AppContainer.characterRepository
                ) as T
            }
        }
    }
}
