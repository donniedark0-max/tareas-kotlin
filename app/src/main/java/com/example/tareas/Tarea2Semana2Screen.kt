package com.example.tareas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.platform.LocalContext


@Preview(showBackground = true)
@Composable
fun Tarea2Semana2Screen(navController: NavController = rememberNavController()) {
    var cuotaMensual by remember { mutableStateOf("") }
    var tasaAnual by remember { mutableStateOf("") }
    var tiempo by remember { mutableStateOf("") }
    var precioActual by remember { mutableStateOf("") }
    val context = LocalContext.current

// Interfaz de usuario para la tarea 2 de la semana 2
    Surface( //
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
                value = cuotaMensual,
                onValueChange = { cuotaMensual = it },
                label = { Text("Cuota Mensual",
                    color = Color.Black
                ) },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                )
                )

            OutlinedTextField(
                value = tasaAnual,
                onValueChange = { tasaAnual = it },
                label = { Text("Tasa Anual (%)",
                    color = Color.Black
                ) },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                )
            )

            OutlinedTextField(
                value = tiempo,
                onValueChange = { tiempo = it },
                label = { Text("Tiempo (meses)",
                    color = Color.Black
                ) },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                )
            )


            Button(
                onClick = {
                    if (cuotaMensual.isNotEmpty() && tasaAnual.isNotEmpty() && tiempo.isNotEmpty() &&
                        cuotaMensual.matches(Regex("[0-9]+(\\.[0-9]+)?")) && // Expresión regular para números con decimales
                        tasaAnual.matches(Regex("-?[0-9]+(\\.[0-9]+)?")) &&
                        tiempo.all { it.isDigit() }
                        ) {
                    val cuotaMensualFloat = cuotaMensual.toFloatOrNull() ?: 0f
                    val tasaAnualFloat = tasaAnual.toFloatOrNull() ?: 0f
                    val tiempoInt = tiempo.toIntOrNull() ?: 0

                    val tem = calcularTEM2(tasaAnualFloat)
                    val precioActualFloat = calcularValorPresente(cuotaMensualFloat, tem, tiempoInt)

                    precioActual = String.format("%.2f", precioActualFloat)
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
                    .padding(2.dp, top = 20.dp)
            ) {
                Text(
                    "Calcular",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Precio Actual: $precioActual",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )


            Button(
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAEEF7),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 105.dp, top = 80.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, // Reemplaza con el ícono que desees
                    contentDescription = "Ícono de siguiente",
                    Modifier
                        .size(21.dp)
                        .padding(end = 9.dp)
                )
                Text(
                    "Regresar a la tarea anterior",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

            }
        }
    }
}

fun calcularTEM2(tasaAnual: Float): Float {
    val tea = tasaAnual / 100
    return (Math.pow((1 + tea).toDouble(), 1.0 / 12.0) - 1).toFloat()
}

fun calcularValorPresente(cuotaMensual: Float, tem: Float, tiempo: Int): Double {
    return cuotaMensual * (1 - Math.pow((1 + tem).toDouble(), -tiempo.toDouble())) / tem
}