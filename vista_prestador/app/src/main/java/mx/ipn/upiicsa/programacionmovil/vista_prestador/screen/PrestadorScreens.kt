package mx.ipn.upiicsa.programacionmovil.vista_prestador

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun FondoArcoRojo(contenido: @Composable ColumnScope.() -> Unit) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)
    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = rojoSuperior.copy(alpha = 0.7f),
                startAngle = 0f, sweepAngle = 180f, useCenter = true,
                topLeft = Offset(-size.width * 0.1f, -size.height * 0.45f),
                size = androidx.compose.ui.geometry.Size(size.width * 1.2f, size.height * 0.9f)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = contenido
        )
    }
}

@Composable
fun LoginPrestadorScreen(navController: NavHostController) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    FondoArcoRojo {
        Spacer(modifier = Modifier.height(60.dp))
        Text("ACCESO STAFF", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(80.dp))
        Card(shape = RoundedCornerShape(25.dp), colors = CardDefaults.cardColors(Color.White)) {
            Column(modifier = Modifier.padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(value = user, onValueChange = { user = it }, label = { Text("Usuario") }, leadingIcon = { Icon(Icons.Default.Person, null) }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(value = pass, onValueChange = { pass = it }, label = { Text("Contraseña") }, leadingIcon = { Icon(Icons.Default.Lock, null) }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
                if (errorMsg.isNotEmpty()) Text(errorMsg, color = Color.Red, fontSize = 12.sp)
                Button(
                    onClick = {
                        if (user == PrestadorRepository.PRESTADOR_USER && pass == PrestadorRepository.PRESTADOR_PASS) {
                            navController.navigate("agenda") { popUpTo("login") { inclusive = true } }
                        } else { errorMsg = "Credenciales incorrectas" }
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 25.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD44144))
                ) { Text("INGRESAR") }
            }
        }
    }
}

@Composable
fun AgendaScreen(navController: NavHostController) {
    FondoArcoRojo {
        Spacer(modifier = Modifier.height(20.dp))
        Text("MI AGENDA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(140.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            PrestadorRepository.listaCitas.forEach { cita ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable { navController.navigate("detalle/${cita.id}") },
                    shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(Color.White)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Schedule, null, tint = Color(0xFFD44144))
                        Spacer(modifier = Modifier.width(15.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(cita.hora, fontWeight = FontWeight.Black, color = Color(0xFFD44144))
                            Text(cita.cliente, fontWeight = FontWeight.Bold)
                        }
                        Icon(Icons.Default.ChevronRight, null, tint = Color.LightGray)
                    }
                }
            }
        }
    }
}

@Composable
fun DetalleCitaScreen(navController: NavHostController, citaId: Int) {
    val cita = PrestadorRepository.listaCitas.find { it.id == citaId }
    FondoArcoRojo {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White) }
            Text("DETALLES", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(130.dp))
        cita?.let {
            Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Cliente: ${it.cliente}", fontWeight = FontWeight.Bold)
                    Text("Servicio: ${it.servicio}")
                    Text("Hora: ${it.hora}")
                    Text("Teléfono: ${it.telefono}")
                    HorizontalDivider()
                    Text("Notas: ${it.notas}", color = Color.Gray)
                }
            }
        }
    }
}