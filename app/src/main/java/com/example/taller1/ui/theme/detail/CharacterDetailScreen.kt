package com.example.taller1.ui.theme.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taller1.model.Character
import com.example.taller1.model.mockCharacter

//BONO========================================================
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue

import androidx.compose.foundation.background

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.foundation.Canvas
//BONO========================================================

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onBackClick: () -> Unit
) {
    val ctx = LocalContext.current

    // PASO 1 BONO: gradiente animado (Brush)
    val infinite = rememberInfiniteTransition()
    val offsetX by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00C2FF), // cian
            Color(0xFF7C4DFF), // morado
            Color(0xFF00E676)  // verde
        ),
        start = Offset(offsetX, 0f),
        end = Offset(offsetX + 300f, 600f)
    )

    // PASO 2 BONO: fondo gradiente animado a pantalla completa
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Detalle") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen redonda + sombra + borde gradiente animado (BONO)
                Box(
                    modifier = Modifier.size(220.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        // aro gradiente (externo)
                        drawCircle(
                            brush = animatedGradient,
                            radius = size.minDimension / 2
                        )
                        // “corte” interno para que se vea como borde (aro)
                        drawCircle(
                            color = Color.White.copy(alpha = 0.85f),
                            radius = size.minDimension / 2 - 10.dp.toPx()
                        )
                    }

                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .shadow(12.dp, CircleShape)
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${character.id}")
                                }
                                ctx.startActivity(intent)
                            },
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        brush = animatedGradient
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 6+ campos (incluye origin.name y location.name)
                InfoRow(label = "Estado", value = character.status)
                InfoRow(label = "Especie", value = character.species)
                InfoRow(label = "Género", value = character.gender)
                InfoRow(label = "Ubicación", value = character.location.name)
                InfoRow(label = "Origen", value = character.origin.name)
                InfoRow(label = "ID", value = character.id.toString())
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(
        character = mockCharacter(),
        onBackClick = {}
    )
}