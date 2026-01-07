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
fun CrearCuentaScreen(navController: NavHostController) {
    val fondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val cuadroBotones = Color(0xFFB6CCFF)
    val botonColor = Color(0xFFD44144)

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            Text("Crear cuenta", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre completo:") }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo electrónico:") }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña:") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White))
            Spacer(modifier = Modifier.height(12.dp))
            TextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirmar contraseña:") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White))

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.background(cuadroBotones, RoundedCornerShape(20.dp)).padding(16.dp)) {
                Button(
                    onClick = {
                        if (nombre.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                            val nuevoUsuario = Usuario(nombre, correo, password)
                            UserSession.usuariosRegistrados[correo] = nuevoUsuario
                            // --- LÍNEA CLAVE: ASIGNAR AL REGISTRARSE ---
                            UserSession.usuarioActual = nuevoUsuario
                            navController.navigate("login")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar", color = Color.White)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text("¿Ya tienes cuenta? Inicia sesión", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().clickable { navController.navigate("login") })
            }
        }
    }
}