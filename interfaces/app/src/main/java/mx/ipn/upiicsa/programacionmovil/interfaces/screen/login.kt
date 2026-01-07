package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    val fondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val cuadroBotones = Color(0xFFB6CCFF)
    val botonColor = Color(0xFFD44144)

    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorLogin by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(fondo)) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.statusBarsPadding().padding(16.dp).align(Alignment.TopStart)
        ) {
            Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "Volver", tint = Color.Black)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center).background(cuadroClaro, RoundedCornerShape(30.dp)).padding(24.dp).fillMaxWidth(0.85f)
        ) {
            Text("Iniciar Sesión", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = correo,
                onValueChange = { correo = it; errorLogin = false },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it; errorLogin = false },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White)
            )

            if (errorLogin) {
                Text(text = "Correo o contraseña incorrectos", color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(modifier = Modifier.background(cuadroBotones, RoundedCornerShape(20.dp)).padding(16.dp)) {
                Button(
                    onClick = {
                        val usuarioEncontrado = UserSession.usuariosRegistrados[correo]
                        if (usuarioEncontrado != null && usuarioEncontrado.contrasena == password) {
                            // --- LÍNEA CLAVE: ASIGNAR USUARIO ACTUAL ---
                            UserSession.usuarioActual = usuarioEncontrado
                            navController.navigate("iniciosincitas")
                        } else {
                            errorLogin = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar", color = Color.White)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text("¿Olvidaste tu contraseña?", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().clickable { navController.navigate("cambiarcontraseña") })
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("¿No tienes cuenta? Regístrate aquí", modifier = Modifier.clickable { navController.navigate("registro") })
        }
    }
}