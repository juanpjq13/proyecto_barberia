package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun cambiarcontraseñaScreen(navController: NavHostController) { // Agregamos NavController
    val fondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val botonColor = Color(0xFFD44144)

    // Corregimos los estados para que sean independientes y oculten el texto
    var nuevaPassword by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(cuadroClaro, RoundedCornerShape(30.dp))
                .padding(24.dp)
                .fillMaxWidth(0.85f)
        ) {
            Text("Recuperar contraseña", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = nuevaPassword,
                onValueChange = { nuevaPassword = it },
                label = { Text("Nueva contraseña:") },
                visualTransformation = PasswordVisualTransformation(), // Oculta la clave
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = confirmarPassword,
                onValueChange = { confirmarPassword = it },
                label = { Text("Confirmar contraseña:") },
                visualTransformation = PasswordVisualTransformation(), // Oculta la clave
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón Ingresar -> Lleva al Login o Confirmación
            Button(
                onClick = {
                    navController.navigate("login")
                },
                colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón Regresar -> Vuelve a la pantalla anterior
            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Regresar", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewcambiarcontraseñaScreen() {
    cambiarcontraseñaScreen(navController = rememberNavController())
}