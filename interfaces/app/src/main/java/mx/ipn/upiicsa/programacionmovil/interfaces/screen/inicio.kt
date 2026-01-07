package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mx.ipn.upiicsa.programacionmovil.interfaces.R

@Composable
fun InicioScreen(navController: NavHostController) {
    val fondo = Color(0xFF6C96FF)
    val cuadroClaro = Color(0xFFCFDDFF)
    val cuadroBotones = Color(0xFFB6CCFF)
    val botonColor = Color(0xFFD44144)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(cuadroClaro, RoundedCornerShape(30.dp))
                .padding(20.dp)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.9f)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Barbería",
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡Agenda fácil\nLuce increíble!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .background(cuadroBotones, RoundedCornerShape(30.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botón Crear Cuenta -> Registro
                Button(
                    onClick = { navController.navigate("registro") },
                    colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Crear cuenta", color = Color.White)
                }

                // Botón Iniciar Sesión -> Redirecciona a Login (login.kt)
                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(containerColor = botonColor),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Iniciar sesión", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInicioScreen() {
    InicioScreen(navController = rememberNavController())
}