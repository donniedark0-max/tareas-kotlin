package com.example.tareas

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt

@SuppressLint("DefaultLocale")
@Preview(showBackground = true)
@Composable
fun TareaSemana2Screen(navController: NavController = rememberNavController()) {
    var precio by remember { mutableStateOf("") }
    var tasaAnual by remember { mutableStateOf("")}
    var tiempo by remember { mutableStateOf("") }
    var mostrarDecimales by remember { mutableStateOf(false) }
    var tasaMensual by remember { mutableStateOf("") }
    var cuotaMensual by remember { mutableStateOf("") }

    val context = LocalContext.current


    // Interfaz de usuario para la tarea semana 2
    Surface( // Usa Surface como contenedor principal
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB) // Ocupa todo el espacio disponible
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Text(
                text = "Calculadora de Crédito",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                .padding(0.dp, 35.dp, 0.dp, 20.dp)
            )

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tasaAnual,
                onValueChange = { tasaAnual = it },
                label = { Text("Tasa Anual (%)") },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tiempo,
                onValueChange = { tiempo = it },
                label = { Text("Tiempo (meses)") },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = mostrarDecimales,
                    onCheckedChange = { mostrarDecimales = it }
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder, // Reemplaza con el ícono que desees
                    contentDescription = "Ícono de información",
                    Modifier.size(12.dp)
                )
                Text("Mostrar decimales",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(start = 8.dp, )
                )
            }

            Button(
                onClick = {
                    if (precio.isNotEmpty() && tasaAnual.isNotEmpty() && tiempo.isNotEmpty() &&
                        precio.all { it.isDigit() } && tasaAnual.all { it.isDigit() } && tiempo.all { it.isDigit() }
                    ) {
                //codigo para la logica de la tasa efectiva mensual
                    val precioFloat = precio.toFloatOrNull() ?: 0f
                    val tasaAnualFloat = tasaAnual.toFloatOrNull() ?: 0f
                    val tiempoInt = tiempo.toIntOrNull() ?: 0

                    val tem = calcularTEM(tasaAnualFloat)
                    tasaMensual = if (mostrarDecimales) {
                        String.format("%.2f",tem * 100) + "%"
                    } else {
                        "${(tem * 100).roundToInt()}%"
                    }

                    val cuota = calcularCuotaMensual(precioFloat, tem, tiempoInt)
                    cuotaMensual = if (mostrarDecimales) {
                        String.format("%.2f", cuota)
                    } else {
                        "${cuota.roundToInt()}"
                    }
                    } else {
                        // Mostrar un mensaje de error al usuario
                        Toast.makeText(context, "Por favor, ingrese valores numéricos válidos.", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAEEF7),
                        contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text("Calcular",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Text(
                text = "Tasa Mensual: $tasaMensual",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Cuota Mensual: $cuotaMensual",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { navController.navigate("tarea_2_semana_2") },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAEEF7),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start= 190.dp,top = 20.dp)
            ) {
                Text("Ir a la Tarea 2",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, // Reemplaza con el ícono que desees
                    contentDescription = "Ícono de siguiente",
                    Modifier
                        .size(25.dp)
                        .padding(start = 12.dp)
                )
                    }
            Tip2()
        }
    }
}

fun calcularTEM(tasaAnual: Float): Float {
    val tea = tasaAnual / 100
    return (Math.pow((1 + tea).toDouble(), 1.0 / 12.0) - 1).toFloat()
}

fun calcularCuotaMensual(precio: Float, tem: Float, tiempo: Int): Double {
    return (precio * tem) / (1 - Math.pow((1 + tem).toDouble(), -tiempo.toDouble()))
}


@Composable
fun Tip2() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp),
        color = Color(0xFFFFF3DED4),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.StarBorder, // Reemplaza con el ícono que desees
                contentDescription = "Ícono de información",
                Modifier.size(12.dp)
            )
            Text(
                "Si se selecciona, se mostrará el valor de 2 decimales, caso contraro se redondeará al entero más cercano",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end= 5.dp),
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

