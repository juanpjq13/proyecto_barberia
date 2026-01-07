package mx.ipn.upiicsa.programacionmovil.vista_admin

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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

@Composable
fun InicioAdminScreen(navController: NavHostController) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = rojoSuperior.copy(alpha = 0.7f),
                startAngle = 0f, sweepAngle = 180f, useCenter = true,
                topLeft = Offset(-size.width * 0.1f, -size.height * 0.4f),
                size = androidx.compose.ui.geometry.Size(size.width * 1.2f, size.height * 0.9f)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding().padding(start = 20.dp, end = 20.dp, top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("GESTIÓN DE BARBERÍA", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(180.dp))

            AdminSession.funcionalidades.forEach { opcion ->
                Button(
                    // AHORA CADA BOTÓN TIENE SU DIRECCIÓN
                    onClick = { navController.navigate(opcion.ruta) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).height(65.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(2.dp, rojoSuperior)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${opcion.id}. ${opcion.titulo}", color = Color.Black, fontWeight = FontWeight.Medium)
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = rojoSuperior)
                    }
                }
            }
        }
    }
}