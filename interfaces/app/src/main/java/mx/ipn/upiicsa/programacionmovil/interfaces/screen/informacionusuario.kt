package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.BorderStroke
import mx.ipn.upiicsa.programacionmovil.interfaces.R

@Composable
fun InformacionUsuarioScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFCFDDFF)
    val rojoBoton = Color(0xFFC76B6C)

    // Se obtienen los datos reales de la sesión
    val usuarioNombre = UserSession.usuarioActual?.nombre ?: "Sin Nombre"
    val usuarioCorreo = UserSession.usuarioActual?.correo ?: "Sin Correo"

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        // ... (Canvas e Image de fondo iguales)

        // BARRA SUPERIOR
        Box(
            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(start = 20.dp, end = 20.dp, top = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar", tint = Color.Black)
            }
            Box(modifier = Modifier.offset(x = 140.dp).size(60.dp).background(Color.LightGray.copy(alpha = 0.5f), CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color.White)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ... (Foto de usuario circular igual)

            Text(text = usuarioNombre, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = usuarioCorreo, fontSize = 18.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(15.dp))
            OutlinedButton(onClick = {
                UserSession.usuarioActual = null // Limpiar sesión al salir
                navController.navigate("login")
            }, border = BorderStroke(2.dp, rojoBoton), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.8f).height(55.dp)) {
                Text("Cerrar Sesión", color = Color.Black)
            }
        }
    }
}