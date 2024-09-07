package com.example.tareas

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Tarea2ImprimirActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val alumno = intent.getStringExtra("alumno") ?: ""
            val escuela = intent.getStringExtra("escuela") ?: ""
            val carrera = intent.getStringExtra("carrera") ?: ""
            val gastosAdicionales = intent.getStringExtra("gastosAdicionales") ?: ""
            val montoGastosAdicionales = intent.getStringExtra("montoGastosAdicionales") ?: ""
            val numCuotas = intent.getIntExtra("numCuotas", 0)
            val costoCarrera = intent.getStringExtra("costoCarrera") ?: ""
            val pension = intent.getStringExtra("pension") ?: ""
            val totalPagar = intent.getStringExtra("totalPagar") ?: ""
            val carnetBiblioteca = intent.getBooleanExtra("carnetBiblioteca", false)
            val carnetMedioPasaje = intent.getBooleanExtra("carnetMedioPasaje",false)
            Tarea2Semana3ImprimirScreen(
                alumno,
                escuela,
                carrera,
                gastosAdicionales,
                montoGastosAdicionales,
                numCuotas,
                costoCarrera,
                pension,
                totalPagar,
                carnetBiblioteca,
                carnetMedioPasaje
            )
        }
    }
}

// Tarea2Semana3ImprimirScreen.kt
@Composable
fun Tarea2Semana3ImprimirScreen(
    alumno: String,
    escuela: String,
    carrera: String,
    gastosAdicionales: String,
    montoGastosAdicionales: String,
    numCuotas: Int,
    costoCarrera: String,
    pension: String,
    totalPagar: String,
    carnetBiblioteca: Boolean = false,
    carnetMedioPasaje: Boolean = false

) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Resumen Matrícula", style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 25.dp, 0.dp, 10.dp))
            Text("Alumno: $alumno",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Escuela: $escuela",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Carrera: $carrera",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)

            val gastosAdicionalesTexto = when {
                carnetBiblioteca && carnetMedioPasaje -> "Carnet Biblioteca y Carnet Medio Pasaje"
                carnetBiblioteca -> "Carnet Biblioteca"
                carnetMedioPasaje -> "Carnet MedioPasaje"
                else -> "Ninguno"
            }
            Text("Gastos adicionales: $gastosAdicionalesTexto",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Número cuotas: $numCuotas",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Costo carrera: S/. $costoCarrera",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Pensión: S/. $pension",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Monto gastos adicionales: S/. $montoGastosAdicionales",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Total a pagar: S/. $totalPagar",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}