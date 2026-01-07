package mx.ipn.upiicsa.programacionmovil.vista_admin

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController // IMPORTANTE

@Composable
fun LoginAdminScreen(navController: NavHostController) {
    val azulFondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val rojoBoton = Color(0xFFD44144)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(azulFondo), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(cuadroClaro, RoundedCornerShape(30.dp))
                .padding(24.dp)
        ) {
            Text("Panel Administrativo", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = email, onValueChange = { email = it }, label = { Text("Correo Admin") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())

            if (errorMsg.isNotEmpty()) {
                Text(errorMsg, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email == AdminSession.ADMIN_EMAIL && password == AdminSession.ADMIN_PASSWORD) {
                        navController.navigate("inicio_admin")
                    } else {
                        errorMsg = "Credenciales incorrectas"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = rojoBoton),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Entrar", color = Color.White)
            }
        }
    }
}