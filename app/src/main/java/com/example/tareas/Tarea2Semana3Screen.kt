package com.example.tareas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.text.TextStyle


@Preview
@Composable
fun Tarea2Semana3Screen(context: Context = LocalContext.current) {
    var alumno by remember { mutableStateOf("") }
    var escuela by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    var costoCarrera by remember { mutableDoubleStateOf(0.0) }
    var pension by remember { mutableDoubleStateOf(0.0) }
    var gastosAdicionales by remember { mutableDoubleStateOf(0.0) }
    var totalPagar by remember { mutableDoubleStateOf(0.0) }
    var numCuotas by remember { mutableStateOf(4) }
    var carnetBiblioteca by remember { mutableStateOf(false) }
    var carnetMedioPasaje by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB)
    ) {
        Column(modifier = Modifier.padding(25.dp)) {
            Text(
                "Matrícula",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 35.dp, bottom = 10.dp)
            )
            OutlinedTextField(
                value = alumno,
                onValueChange = { alumno = it },
                label = { Text("Alumno",
                    color = Color.Black
                ) },
                        keyboardOptions = KeyboardOptions
                        (keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                )
            )

            EscuelaCarreraDropdown(  escuela = escuela,
                onEscuelaChange = { escuela = it },
                carrera = carrera,
                onCarreraChange = { carrera = it },
                costoCarrera = costoCarrera,
                onCostoCarreraChange = { costoCarrera = it }
            )


                Text(
                    text = "Gastos adicionales",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
                    Checkbox(
                        checked = carnetBiblioteca,
                        onCheckedChange = { isChecked ->
                            carnetBiblioteca = isChecked
                            if (isChecked) {
                                gastosAdicionales += 25.0
                            } else {
                                gastosAdicionales -= 25.0
                            }
                        }
                    )
                    Icon(
                        imageVector = Icons.Filled.StarBorder,
                        contentDescription = "Ícono de información",
                        Modifier.size(12.dp)
                    )
                    Text("Carnet biblioteca",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = carnetMedioPasaje,
                            onCheckedChange = { isChecked ->
                                carnetMedioPasaje = isChecked
                                if (isChecked) {
                                    gastosAdicionales += 22.0
                                } else {
                                    gastosAdicionales -= 22.0
                                }
                            }
                        )
                        Icon(
                            imageVector = Icons.Filled.StarBorder,
                            contentDescription = "Ícono de información",
                            Modifier.size(12.dp)
                        )
                        Text("Carnet medio pasaje",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                Text(
                    text = "Número de cuotas",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = numCuotas == 4,
                        onClick = { numCuotas = 4 },
                        modifier = Modifier
                            .padding(start = 10.dp, end = 2.dp)
                    )
                    Text("4")
                    RadioButton(
                        selected = numCuotas == 5,
                        onClick = { numCuotas = 5 },
                        modifier = Modifier
                            .padding(start = 65.dp, end = 2.dp)
                    )
                    Text("5")
                    RadioButton(
                        selected = numCuotas == 6,
                        onClick = { numCuotas = 6 },
                        modifier = Modifier
                            .padding(start = 65.dp, end = 2.dp)
                    )
                    Text("6")
                }

                Text("Costo carrera: S/. $costoCarrera",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold)
                Text("Pensión: S/. ${String.format("%.2f", pension)}",
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold)
                Text("Gastos adicionales: S/. $gastosAdicionales",
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold)
                Text("Total a pagar: S/. $totalPagar",
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold)

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        if (validarCampos2(context, alumno)) {
                            val porcentaje = when (numCuotas) {
                                4 -> 0.0
                                5 -> 0.12
                                6 -> 0.16
                                else -> 0.0
                            }
                            pension =
                                (costoCarrera + (costoCarrera * porcentaje)) / numCuotas //Cálculo de la pensión
                            gastosAdicionales = when {
                                carnetBiblioteca && carnetMedioPasaje -> 47.0
                                carnetBiblioteca -> 25.0
                                carnetMedioPasaje -> 22.0
                                else -> 0.0
                            }
                            totalPagar =
                                costoCarrera + (costoCarrera * porcentaje) + gastosAdicionales
                        }
                    },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFAEEF7),
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .padding(2.dp)
                    )  {
                        Text("Calcular",
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (validarCampos2(context, alumno)) {
                            val intent = Intent(context, Tarea2ImprimirActivity::class.java).apply {
                                putExtra("alumno", alumno)
                                putExtra("escuela", escuela)
                                putExtra("carrera", carrera)
                                putExtra("carnetBiblioteca", carnetBiblioteca)
                                putExtra("carnetMedioPasaje", carnetMedioPasaje)
                                putExtra("gastosAdicionales", gastosAdicionales)
                                putExtra(
                                    "montoGastosAdicionales",
                                    String.format("%.2f", gastosAdicionales)
                                )
                                putExtra("numCuotas", numCuotas)
                                putExtra("costoCarrera", String.format("%.2f", costoCarrera))
                                putExtra("pension", String.format("%.2f", pension))
                                putExtra("totalPagar", String.format("%.2f", totalPagar))
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
                            .padding(2.dp)
                    ) {
                        Text("Imprimir",
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold)
                    }
                }
            Tip3()
            }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscuelaCarreraDropdown(
    escuela: String,
    onEscuelaChange: (String) -> Unit,
    carrera: String,
    onCarreraChange: (String) -> Unit,
    costoCarrera: Double,
    onCostoCarreraChange: (Double) -> Unit) {
    val context = LocalContext.current
    var expandedEscuela by remember { mutableStateOf(false) }
    val escuelas = listOf("Tecnologías de la Información", "Gestión y Negocios")
    var escuela by remember { mutableStateOf("") }

    var expandedCarrera by remember { mutableStateOf(false) }
    var carrera by remember { mutableStateOf("") }
    val carreras = when (escuela) {
        "Tecnologías de la Información" -> listOf(
            "Computación e informática",
            "Administración de redes y comunicaciones",
            "Administración y sistemas",
            "Industrial y sistemas"
        )
        "Gestión y Negocios" -> listOf(
            "Administración de empresas",
            "Contabilidad",
            "Marketing",
            "Administración de negocios internacionales",
            "Administración de negocios bancarios y financieros",
            "Gestión de recursos humanos",
            "Gestión de logística(nueva)",
            "Administración de operaciones turísticas"
        )
        else -> listOf()
    }

    Column(modifier = Modifier.padding( top = 10.dp, bottom = 10.dp)) {
        ExposedDropdownMenuBox(
            expanded = expandedEscuela,
            onExpandedChange = { expandedEscuela = !expandedEscuela }
        ) {
            TextField(
                value = escuela,
                onValueChange = { escuela = it },
                readOnly = true,
                label = { Text("Escuela",
                    color = Color.Black) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEscuela) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(Color(0xFFFAEEF7)),
                        textStyle = TextStyle(color = Color.Black),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color(0xFFFAEEF7),
                    cursorColor = Color.Black,
                )
            )
            ExposedDropdownMenu(
                expanded = expandedEscuela,
                onDismissRequest = { expandedEscuela = false },
                modifier = Modifier.background(Color(0xFFFAEEF7))
            ) {
                escuelas.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption,style = TextStyle(color = Color.Black)) },
                        onClick = {
                            escuela = selectionOption
                            expandedEscuela = false
                            carrera = ""
                            onEscuelaChange(selectionOption)
                        },
                        modifier = Modifier
                            .background(Color(0xFFFAEEF7)),
                        interactionSource = remember { MutableInteractionSource() }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expandedCarrera,
            onExpandedChange = { expandedCarrera = !expandedCarrera }
        ) {
            TextField(
                value = carrera,
                onValueChange = { carrera = it },
                readOnly = true,
                label = { Text("Carrera", style = TextStyle(color = Color.Black)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCarrera) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(Color(0xFFFAEEF7)),
                textStyle = TextStyle(color = Color.Black),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color(0xFFFAEEF7),
                    cursorColor = Color.Black,
                )
            )
            ExposedDropdownMenu(
                expanded = expandedCarrera,
                onDismissRequest = { expandedCarrera = false },
                modifier = Modifier.background(Color(0xFFFAEEF7))
            ) {
                carreras.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption, style = TextStyle(color = Color.Black)) },
                        onClick = {
                            carrera = selectionOption
                            expandedCarrera = false
                            onCostoCarreraChange(obtenerCostoCarrera(escuela, carrera))
                            Toast.makeText(context, selectionOption, Toast.LENGTH_SHORT).show()
                            onCarreraChange(selectionOption)
                        },
                        modifier = Modifier
                            .background(Color(0xFFFAEEF7)),
                    )
                }
            }
        }
    }
}

fun obtenerCostoCarrera(escuela: String, carrera: String): Double {
    val carreras = when (escuela) {
        "Tecnologías de la Información" -> mapOf(
            "Computación e informática" to 3200.0,
            "Administración de redes y comunicaciones" to 3100.0,
            "Administración y sistemas" to 3000.0,
            "Industrial y sistemas" to 0.0
        )
        "Gestión y Negocios" -> mapOf(
            "Administración de empresas" to 2800.0,
            "Contabilidad" to 2700.0,
            "Marketing" to 2600.0,
            "Administración de negocios internacionales" to 2650.0,
            "Administración de negocios bancarios y financieros" to 2750.0,
            "Gestión de recursos humanos" to 2550.0,
            "Gestión de logística (nueva)" to 0.0,
            "Administración de operaciones turísticas" to 0.0
        )
        else -> mapOf()
    }
    return carreras[carrera] ?: 0.0
}


@Composable
fun Tip3() {
    Surface(
        modifier = Modifier
            .padding(top = 30.dp),
        color = Color(0xFFFFF3DED4),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(13.dp)
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
                "Carnet Biblioteca : S/. 25 \n" +
                        "Carnet Medio Pasaje : S/. 22",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 5.dp),
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun validarCampos2(context: Context, alumno: String): Boolean {
    if (alumno.isBlank()) {
        Toast.makeText(context, "Por favor, complete el nombre del alumno.", Toast.LENGTH_SHORT).show()
        return false
    }

    if (!alumno.all { it.isLetter() || it.isWhitespace() }) {
        Toast.makeText(context, "El nombre del alumno solo debe contener letras.", Toast.LENGTH_SHORT).show()
        return false
    }

    return true
}