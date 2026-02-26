package com.example.taller1

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.taller1.navigation.CharacterDetailKey
import com.example.taller1.navigation.TempEntryKey
import com.example.taller1.ui.temp.TempEntryScreen
import com.example.taller1.ui.theme.Taller1Theme
import com.example.taller1.ui.theme.detail.CharacterDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Taller1Theme {
                val activity = LocalContext.current as? Activity

                // Backstack de Navigation3: arranca en la pantalla TEMP
                val backStack = remember { mutableStateListOf<Any>(TempEntryKey) }

                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        } else {
                            activity?.finish()
                        }
                    },
                    entryProvider = entryProvider {
                        entry<TempEntryKey> {
                            TempEntryScreen(
                                onOpenDetail = { character ->
                                    backStack.add(CharacterDetailKey(character))
                                }
                            )
                        }

                        entry<CharacterDetailKey> { key ->
                            CharacterDetailScreen(
                                character = key.character,
                                onBackClick = { backStack.removeAt(backStack.lastIndex) }
                            )
                        }
                    }
                )
            }
        }
    }
}