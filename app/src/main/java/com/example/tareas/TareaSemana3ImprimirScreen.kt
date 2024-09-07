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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ImprimirActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cliente = intent.getStringExtra("cliente") ?: ""
            val dni = intent.getStringExtra("dni") ?: ""
            val servicio = intent.getStringExtra("servicio") ?: ""
            val costoServicio = intent.getStringExtra("costoServicio") ?: ""
            val costoInstalacion = intent.getStringExtra("costoInstalacion") ?: ""
            val descuento = intent.getStringExtra("descuento") ?: ""
            val total = intent.getStringExtra("total") ?: ""
            TareaSemana3ImprimirScreen(cliente, dni, servicio, costoServicio, costoInstalacion, descuento, total)
        }
    }
}

@Composable
fun TareaSemana3ImprimirScreen(
    cliente: String,
    dni: String,
    servicio: String,
    costoServicio: String,
    costoInstalacion: String,
    descuento: String,
    total: String
) {
    Surface( //
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB) // Ocupa todo el espacio disponible
    ) {
        Column(modifier = Modifier.padding(25.dp)) {
            Text("Resumen Servicio",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 25.dp, 0.dp, 10.dp)
            )
            Text("Cliente: $cliente",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("DNI: $dni",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Servicio: $servicio",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Costo Servicio: S/ $costoServicio",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Costo Instalación: S/ $costoInstalacion",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Descuento: $descuento%",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text("Total a Pagar: S/ $total",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}