package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class Trabajador(val nombre: String, val especialidad: String, val experiencia: String)

@Composable
fun NuestroEquipoScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFCFDDFF)
    val azulCard = Color(0xFFE9F0FF)

    val equipo = listOf(
        Trabajador("Carlos Ruíz", "Corte de cabello", "8 años exp."),
        Trabajador("Ana López", "Masaje capilar", "5 años exp."),
        Trabajador("Luis Pérez", "Afeitado de barba", "10 años exp."),
        Trabajador("Sofía Marín", "Tratamiento capilar", "4 años exp.")
    )

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(color = rojoSuperior.copy(alpha = 0.6f), startAngle = 0f, sweepAngle = 180f, useCenter = true,
                topLeft = Offset(-size.width * 0.3f, -size.height * 0.5f), size = androidx.compose.ui.geometry.Size(size.width * 1.6f, size.height))
        }

        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.statusBarsPadding().padding(8.dp)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
        }

        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nuestro Equipo", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(30.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(equipo) { empleado ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = azulCard),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(160.dp).fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(empleado.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(empleado.especialidad, color = rojoSuperior, fontWeight = FontWeight.Medium, fontSize = 13.sp, textAlign = TextAlign.Center)
                            Text(empleado.experiencia, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

