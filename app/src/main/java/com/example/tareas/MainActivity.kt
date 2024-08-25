package com.example.tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tareas.ui.theme.TareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lista_tareas") {
        composable("lista_tareas") { Navigation(navController) }
        composable("tarea_semana_2") { TareaSemana2Screen() }
        composable("tarea_semana_3") { TareaSemana3Screen() }
        composable("tarea_semana_4") { TareaSemana4Screen() }
        // Agrega más composables para otras tareas
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Tareas") })
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
           items(listaDeTareas) { tarea ->
               TareaItem(navController, tarea)
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
        modifier = Modifier.padding(8.dp)
    ) {
        Text(tarea)
    }
}
