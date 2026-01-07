package mx.ipn.upiicsa.programacionmovil.vista_admin

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController

@Composable
fun PrestadoresScreen(navController: NavHostController) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)

    // Estados para eliminación
    var modoEliminar by remember { mutableStateOf(false) }
    var prestadorSeleccionado by remember { mutableStateOf<String?>(null) }

    // Estados para añadir nuevo
    var mostrarFormulario by remember { mutableStateOf(false) }
    var nuevoNombre by remember { mutableStateOf("") }
    var nuevaSucursal by remember { mutableStateOf("") }
    var nuevoCorreo by remember { mutableStateOf("") }

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    if (modoEliminar || mostrarFormulario) {
                        modoEliminar = false; mostrarFormulario = false; prestadorSeleccionado = null
                    } else { navController.popBackStack() }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Text(
                    text = if (mostrarFormulario) "NUEVO PRESTADOR" else "GESTIONAR PRESTADORES",
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                // Botón para mostrar formulario
                IconButton(onClick = {
                    mostrarFormulario = !mostrarFormulario
                    modoEliminar = false
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(140.dp))

            if (mostrarFormulario) {
                // --- FORMULARIO PARA AÑADIR ---
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(value = nuevoNombre, onValueChange = { nuevoNombre = it }, label = { Text("Nombre completo") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = nuevaSucursal, onValueChange = { nuevaSucursal = it }, label = { Text("Sucursal asignada") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = nuevoCorreo, onValueChange = { nuevoCorreo = it }, label = { Text("Correo electrónico") }, modifier = Modifier.fillMaxWidth())

                        Button(
                            onClick = {
                                if (nuevoNombre.isNotBlank() && nuevaSucursal.isNotBlank()) {
                                    AdminSession.listaPrestadores.add(Prestador(nuevoNombre, nuevaSucursal, nuevoCorreo))
                                    // Limpiar y cerrar
                                    nuevoNombre = ""; nuevaSucursal = ""; nuevoCorreo = ""; mostrarFormulario = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Verde para éxito
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Guardar Prestador", color = Color.White)
                        }
                    }
                }
            } else {
                // --- LISTA DE PRESTADORES ---
                AdminSession.listaPrestadores.forEach { prestador ->
                    val esSeleccionado = prestadorSeleccionado == prestador.nombre
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable(enabled = modoEliminar) { prestadorSeleccionado = prestador.nombre },
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = if (esSeleccionado) Color(0xFFFFEBEE) else Color.White),
                        border = if (esSeleccionado) BorderStroke(2.dp, rojoSuperior) else null
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(if (esSeleccionado) Icons.Default.CheckCircle else Icons.Default.Person, contentDescription = null, tint = rojoSuperior, modifier = Modifier.size(35.dp))
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(prestador.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("📍 ${prestador.sucursalAsignada}", fontSize = 12.sp, color = rojoSuperior)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón de Eliminar (Solo visible si no estamos añadiendo)
            if (!mostrarFormulario) {
                Button(
                    onClick = {
                        if (!modoEliminar) { modoEliminar = true }
                        else if (prestadorSeleccionado != null) {
                            AdminSession.listaPrestadores.removeIf { it.nombre == prestadorSeleccionado }
                            modoEliminar = false; prestadorSeleccionado = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = if (prestadorSeleccionado != null) Color.Black else rojoSuperior),
                    modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth(0.8f).height(50.dp),
                    enabled = AdminSession.listaPrestadores.isNotEmpty() && (!modoEliminar || prestadorSeleccionado != null)
                ) {
                    Text(
                        text = when {
                            prestadorSeleccionado != null -> "CONFIRMAR ELIMINACIÓN"
                            modoEliminar -> "SELECCIONE UNO"
                            else -> "ELIMINAR PRESTADOR"
                        },
                        color = Color.White, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}