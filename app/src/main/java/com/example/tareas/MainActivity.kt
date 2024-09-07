package com.example.tareas

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tareas.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lista_tareas") {
        composable("lista_tareas") { Navigation(navController) }
        composable("tarea_semana_2") { TareaSemana2Screen(navController) }
        composable("tarea_semana_3") { TareaSemana3Screen(LocalContext.current, navController) }
        composable("tarea_2_semana_3") { Tarea2Semana3Screen(LocalContext.current) }
        composable("tarea_semana_4") { TareaSemana4Screen() }
        composable("tarea_2_semana_2") { Tarea2Semana2Screen(navController) }       // Agrega más composables para otras tareas
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavController) {
    Surface( // Usa Surface como contenedor principal
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9FB) // Ocupa todo el espacio disponible
    ) {
        Column {
            Box(modifier = Modifier.padding(top = 50.dp)) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFFFFF9FB),
                        titleContentColor = Color(0xFF000000),
                    ),
                    title = {
                        Text(
                            "Mis Tareas",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 40.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.W500
                        )
                    })
                Box(modifier = Modifier.padding(top = 48.dp)) {

                    Tip() //Llamando a l funcion Tip()
                }
                Box(modifier = Modifier.padding(top = 140.dp)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), // Agrega padding alrededor de la cuadrícula
                        verticalArrangement = Arrangement.Top // Alinea los elementos al principio
                    ) {
                        items(listaDeTareas) { tarea ->
                            TareaItem(navController, tarea)
                        }
                    }
                }
            }
        }
    }
}
        val listaDeTareas = listOf(
    "Tarea Semana 2",
    "Tarea Semana 3",
    "Tarea Semana 4",
    "Tarea Semana 5",
    "Tarea Semana 6",
    "Tarea Semana 7"
)

@Composable
fun TareaItem(navController: NavController, tarea: String) {
    Button(
        onClick = {navController.navigate(tarea.lowercase().replace(" ", "_")) },
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFAEEF7), // Cambia el color de fondo
            contentColor = Color.Black // Cambia el color del texto
        )
    ) {
        Text(
            tarea,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Tip() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
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
                imageVector = Icons.Filled.Info, // Reemplaza con el ícono que desees
                contentDescription = "Ícono de información",
                Modifier.size(20.dp)
            )
            Text(
                "Se irán agregando tareas a lo largo del semestre.",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}