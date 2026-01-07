package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController

@Composable
fun RevisarCitasScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFE9EFFD)
    val rojoBoton = Color(0xFFC76B6C)
    val azulContenedor = Color(0xFFDDE6FE)

    // Obtenemos la cita guardada del objeto global
    val cita = UserSession.citaActual

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        // Fondo decorativo (Arco Rojo)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = rojoSuperior.copy(alpha = 0.6f),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(-size.width * 0.3f, -size.height * 0.5f),
                size = androidx.compose.ui.geometry.Size(size.width * 1.6f, size.height)
            )
        }

        // Botón Volver
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp)
                .align(Alignment.TopStart)
                .zIndex(1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mis Citas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // CARD DE DETALLES DE CITA
            Card(
                colors = CardDefaults.cardColors(containerColor = azulContenedor),
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    if (cita != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = rojoSuperior,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Detalles de tu cita",
                                fontWeight = FontWeight.Bold,
                                color = rojoSuperior,
                                fontSize = 16.sp
                            )
                        }

                        Divider(
                            modifier = Modifier.padding(vertical = 16.dp),
                            color = Color.White.copy(alpha = 0.6f)
                        )

                        // Información de la cita incluyendo la Sucursal
                        CitaInfoRow(label = "Sucursal:", value = cita.sucursal)
                        CitaInfoRow(label = "Servicio:", value = cita.servicio)
                        CitaInfoRow(label = "Fecha:", value = cita.fecha)
                        CitaInfoRow(label = "Horario:", value = cita.hora)

                        Spacer(modifier = Modifier.height(32.dp))

                        // Botón para cancelar
                        Button(
                            onClick = {
                                // Borramos la cita actual y regresamos al inicio vacío
                                UserSession.citaActual = null
                                navController.navigate("iniciosincitas") {
                                    popUpTo("iniciosincitas") { inclusive = true }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = rojoBoton),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                "Cancelar Cita",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        // Estado cuando no hay cita agendada
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No tienes citas agendadas actualmente.",
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            TextButton(onClick = { navController.navigate("agendar") }) {
                                Text("Agendar ahora", color = rojoSuperior, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CitaInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray,
            modifier = Modifier.width(90.dp),
            fontSize = 15.sp
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 15.sp
        )
    }
}