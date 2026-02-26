package com.example.taller1.ui.temp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.taller1.model.Character
import com.example.taller1.model.mockCharacter

@Composable
fun TempEntryScreen(onOpenDetail: (Character) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla temporal", style = MaterialTheme.typography.titleLarge)
        Button(onClick = { onOpenDetail(mockCharacter()) }) {
            Text("Ir al detalle")
        }
    }
}
