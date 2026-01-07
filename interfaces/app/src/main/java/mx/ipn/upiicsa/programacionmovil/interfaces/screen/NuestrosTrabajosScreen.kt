package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class ServicioInfo(val nombre: String, val descripcion: String, val precio: String)

@Composable
fun NuestrosTrabajosScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFCFDDFF)
    val azulCard = Color(0xFFE9F0FF)

    val servicios = listOf(
        ServicioInfo("Corte de cabello", "Corte clásico o moderno con terminación a navaja y estilizado.", "$180 - $350 MXN"),
        ServicioInfo("Recorte o afeitado de barba", "Perfilado de barba con toalla caliente y bálsamos hidratantes.", "$150 - $280 MXN"),
        ServicioInfo("Masaje o tratamiento capilar", "Masaje relajante y exfoliación para fortalecer el cuero cabelludo.", "$200 - $450 MXN")
    )

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(color = rojoSuperior.copy(alpha = 0.6f), startAngle = 0f, sweepAngle = 180f, useCenter = true,
                topLeft = Offset(-size.width * 0.3f, -size.height * 0.5f), size = androidx.compose.ui.geometry.Size(size.width * 1.6f, size.height))
        }

        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.statusBarsPadding().padding(8.dp)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
        }

        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp).padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nuestros Servicios", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(servicios) { servicio ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = azulCard),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(servicio.nombre, fontSize = 19.sp, fontWeight = FontWeight.Bold, color = rojoSuperior)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(servicio.descripcion, fontSize = 14.sp, color = Color.DarkGray)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Precio: ${servicio.precio}", fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
