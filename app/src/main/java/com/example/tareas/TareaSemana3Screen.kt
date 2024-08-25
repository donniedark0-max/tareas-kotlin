package com.example.tareas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TareaSemana3Screen() {
    // Interfaz de usuario para la tarea semana 2
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Tarea Semana 3",
            style = MaterialTheme.typography.headlineMedium
        )
        // Agrega aquí el contenido de la tarea semana 2
    }
}