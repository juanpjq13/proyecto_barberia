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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun confirmacionrecuperacionScreen() {
    val fondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val cuadroBotones = Color(0xFFB6CCFF)
    val botonColor = Color(0xFFD44144)

    var correo by remember { mutableStateOf("") }

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

            Text(
                "Introduce el código que se  ha enviado a tu correo electrónico:",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Código de recuperación: ") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("regresar", color = Color.White)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewconfirmacionrecuperacionScreen() {
    confirmacionrecuperacionScreen()
}