package com.example.tareas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TareaSemana3Screen(context: Context, navController: NavController) {
    var cliente by remember { mutableStateOf("") }
    var dni by remember{ mutableStateOf("") }
    var servicio by remember { mutableStateOf("") }
    var costoServicio by remember { mutableStateOf(0.0) }
    var costoInstalacion by remember { mutableStateOf(0.0) }
    var descuento by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }


    Surface( //
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB) // Ocupa todo el espacio disponible
    ) {
        Column(modifier = Modifier.padding(25.dp)) {
            Text(
                text = "Datos de cliente",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 25.dp, 0.dp, 10.dp)
                )
            OutlinedTextField(
                value = cliente,
                onValueChange = { cliente = it },
                label = {
                    Text(
                        "Cliente",
                        color = Color.Black
                    )
                },
                keyboardOptions = KeyboardOptions
                    (keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                )
            )

            OutlinedTextField(
                value = dni,
                onValueChange = { dni = it },
                label = { Text("DNI",
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
            Text(
                text = "Servicio",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 20.dp)
            )
            // RadioGroup
            Column {
                val opciones = listOf(
                    "Dúo - Cámaras de Seguridad Y Alarmas contra Robos",
                    "Trio - Cámaras de Seguridad - Alarmas contra Robos - Alarmas contra Incendio",
                    "Solo Cámaras de Seguridad",
                    "Solo Alarmas contra Robos",
                    "Solo Alarmas contra Incendio",
                    "Solo Cercos Eléctricos"
                )
                opciones.forEach { opcion ->
                    Row {
                        RadioButton(
                            selected = servicio == opcion,
                            onClick = {
                                servicio = opcion
                                when (opcion) {
                                    opciones[0] -> {
                                        costoServicio = 119.30
                                        costoInstalacion = 37.00
                                        descuento = 0.07
                                    }

                                    opciones[1] -> {
                                        costoServicio = 169.80
                                        costoInstalacion = 65.00
                                        descuento = 0.12
                                    }

                                    opciones[2] -> {
                                        costoServicio = 59.50
                                        costoInstalacion = 21.00
                                        descuento = 0.04
                                    }

                                    opciones[3] -> {
                                        costoServicio = 49.20
                                        costoInstalacion = 17.00
                                        descuento = 0.04
                                    }

                                    opciones[4] -> {
                                        costoServicio = 42.30
                                        costoInstalacion = 15.00
                                        descuento = 0.04
                                    }

                                    opciones[5] -> {
                                        costoServicio = 125.70
                                        costoInstalacion = 35.00
                                        descuento = 0.05
                                    }
                                }
                            }
                        )
                        Text(text = opcion)
                    }
                }
            }
            Text("Costo servicio S/: ${costoServicio}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                   .padding(0.dp, 5.dp),
                fontWeight = FontWeight.SemiBold
             )
            Text("Costo instalación S/: ${costoInstalacion}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                   .padding(0.dp, 5.dp),
                fontWeight = FontWeight.SemiBold
             )
            Text("Descuento: ${String.format("%.1f",descuento * 100)}%",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                   .padding(0.dp, 5.dp),
                fontWeight = FontWeight.SemiBold
             )
            Text("Total a pagar S/: ${String.format("%.2f",total)}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                   .padding(0.dp, 5.dp),
                fontWeight = FontWeight.SemiBold
             )
            Row(modifier = Modifier.fillMaxWidth())  {
                Button(onClick = {
                    if (validarCampos(context, cliente, dni)) {
                        val descuentoServicio = costoServicio * descuento
                        total = costoServicio - descuentoServicio + costoInstalacion
                    }
                },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFAEEF7),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding( top = 20.dp)
                        .weight(1f)
                ) { Text("Calcular",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold) }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (validarCampos(context, cliente, dni)) {
                        val intent = Intent(context, ImprimirActivity::class.java).apply {
                            putExtra("cliente", cliente)
                            putExtra("dni", dni)
                            putExtra("servicio", servicio)
                            putExtra("costoServicio", String.format("%.2f", costoServicio))
                            putExtra("costoInstalacion", String.format("%.2f", costoInstalacion))
                            putExtra("descuento", String.format("%.1f", descuento * 100))
                            putExtra("total", String.format("%.2f", total))
                        }
                        context.startActivity(intent)
                    }
                },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFAEEF7),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .weight(1f)
                ) { Text("Imprimir",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold) }
            }

            Button(
                onClick = {
                    navController.navigate("tarea_2_Semana_3")
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB3EC),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding( top= 10.dp)
                    .fillMaxWidth()
            ) {
                Text("Tarea 2")
            }
        }
    }
}

fun validarCampos(context: Context, cliente: String, dni: String): Boolean {

    if (cliente.isBlank() || dni.isBlank()) {
        Toast.makeText(context, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
        return false
    }

    if (dni.length != 8 || !dni.all { it.isDigit() }) {// Mostrar un mensaje de error al usuario (Toast o Snackbar)
        return false
    }

    if (!cliente.all { it.isLetter() || it.isWhitespace() }) {
        Toast.makeText(context, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}