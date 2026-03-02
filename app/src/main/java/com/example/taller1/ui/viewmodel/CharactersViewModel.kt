package com.example.taller1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.taller1.data.AppContainer
import com.example.taller1.data.repository.CharacterRepository
import com.example.taller1.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val DEFAULT_PAGE = 2

sealed class UiState {
    data object Loading : UiState()

    data class Success(val data: List<Character>) : UiState()

    data class Error(val message: String) : UiState()
}

class CharactersViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadCharacters(DEFAULT_PAGE)
    }

    fun loadCharacters(page: Int = DEFAULT_PAGE) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            _uiState.value = try {
                val characters = repository.getCharacters(page = page)
                Log.d(TAG, "Loaded ${characters.size} characters from page=$page")
                UiState.Success(data = characters)
            } catch (exception: Exception) {
                UiState.Error(
                    message = exception.message ?: "No se pudieron cargar los personajes."
                )
            }
        }
    }

    companion object {
        private const val TAG = "CharactersViewModel"

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
