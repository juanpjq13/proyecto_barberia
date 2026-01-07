package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.ipn.upiicsa.programacionmovil.interfaces.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@Composable
fun InicioconcitasScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFCFDDFF)
    val rojoBoton = Color(0xFFC76B6C)

    val cita = UserSession.citaActual

    val fechaActual = remember {
        val sdf = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
        sdf.format(Date())
    }

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
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
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de fondo",
            modifier = Modifier.fillMaxSize().offset(y = (-200).dp).alpha(0.05f),
            contentScale = ContentScale.Fit
        )

        // BARRA SUPERIOR: SOLO CÍRCULO DERECHO CON ICONO DE USUARIO
        Box(
            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(top = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            IconButton(
                onClick = { navController.navigate("informacionusuario") }, // <-- Cambia esto
                modifier = Modifier
                    .offset(x = (160).dp)
                    .size(60.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Usuario",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text(text = fechaActual, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Spacer(modifier = Modifier.height(45.dp))

            Text(text = "Tienes una cita agendada para el día:", fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Text(text = "${cita?.fecha ?: "No definida"} - ${cita?.hora ?: ""}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = rojoSuperior)

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { navController.navigate("revisarcitas") }, colors = ButtonDefaults.buttonColors(containerColor = rojoBoton), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.7f).height(50.dp)) {
                Text("Revisar cita", color = Color.White)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = { navController.navigate("agendarcita") }, colors = ButtonDefaults.buttonColors(containerColor = rojoBoton), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.7f).height(50.dp)) {
                Text("Agendar nueva cita", color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(onClick = { navController.navigate("nuestroequipo") }, modifier = Modifier.fillMaxWidth(0.8f).height(50.dp), shape = RoundedCornerShape(20.dp), border = BorderStroke(2.dp, rojoBoton)) {
                Text(text = "¡Conoce a nuestro equipo!", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(onClick = { navController.navigate("nuestrostrabajos") }, modifier = Modifier.fillMaxWidth(0.8f).height(50.dp), shape = RoundedCornerShape(20.dp), border = BorderStroke(2.dp, rojoBoton)) {
                Text(text = "Nuestros trabajos", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}