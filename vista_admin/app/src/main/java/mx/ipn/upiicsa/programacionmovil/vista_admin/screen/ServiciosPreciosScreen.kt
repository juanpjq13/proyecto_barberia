package mx.ipn.upiicsa.programacionmovil.vista_admin

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ServiciosPreciosScreen(navController: NavHostController) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)

    // Estados
    var modoEliminar by remember { mutableStateOf(false) }
    var servicioSeleccionado by remember { mutableStateOf<String?>(null) }
    var mostrarFormulario by remember { mutableStateOf(false) }
    var editandoPrecioDe by remember { mutableStateOf<String?>(null) }

    // Campos temporales
    var nombreNuevo by remember { mutableStateOf("") }
    var duracionNueva by remember { mutableStateOf("") }
    var precioNuevo by remember { mutableStateOf("") }
    var precioEditadoTemp by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = rojoSuperior.copy(alpha = 0.7f),
                startAngle = 0f, sweepAngle = 180f, useCenter = true,
                topLeft = Offset(-size.width * 0.1f, -size.height * 0.45f),
                size = androidx.compose.ui.geometry.Size(size.width * 1.2f, size.height * 0.9f)
            )
        }

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(horizontal = 20.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    if (modoEliminar || mostrarFormulario || editandoPrecioDe != null) {
                        modoEliminar = false; mostrarFormulario = false; editandoPrecioDe = null
                    } else navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                }
                Text("SERVICIOS Y PRECIOS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                IconButton(onClick = { mostrarFormulario = !mostrarFormulario; modoEliminar = false; editandoPrecioDe = null }) {
                    Icon(Icons.Default.Add, null, tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(140.dp))

            if (mostrarFormulario) {
                // FORMULARIO AÑADIR
                Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color.White)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(value = nombreNuevo, onValueChange = { nombreNuevo = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = duracionNueva, onValueChange = { duracionNueva = it }, label = { Text("Duración") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = precioNuevo, onValueChange = { precioNuevo = it }, label = { Text("Precio") }, modifier = Modifier.fillMaxWidth())
                        Button(
                            onClick = {
                                if (nombreNuevo.isNotBlank()) {
                                    AdminSession.listaServicios.add(Servicio(nombreNuevo, duracionNueva, precioNuevo))
                                    nombreNuevo = ""; duracionNueva = ""; precioNuevo = ""; mostrarFormulario = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
                        ) { Text("Guardar Nuevo") }
                    }
                }
            } else {
                // LISTA DE SERVICIOS
                val scrollState = rememberScrollState()
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    AdminSession.listaServicios.forEachIndexed { index, ser ->
                        val esSeleccionado = servicioSeleccionado == ser.nombre
                        val estaEditando = editandoPrecioDe == ser.nombre

                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                                .clickable(enabled = modoEliminar) { servicioSeleccionado = ser.nombre },
                            shape = RoundedCornerShape(15.dp),
                            colors = CardDefaults.cardColors(if (esSeleccionado) Color(0xFFFFEBEE) else Color.White),
                            border = if (esSeleccionado) BorderStroke(2.dp, rojoSuperior) else null
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(if (esSeleccionado) Icons.Default.CheckCircle else Icons.Default.ContentCut, null, tint = rojoSuperior)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(ser.nombre, fontWeight = FontWeight.Bold)
                                        Text(ser.duracion, fontSize = 12.sp, color = Color.Gray)
                                    }

                                    // BOTÓN EDITAR PRECIO
                                    if (!modoEliminar) {
                                        IconButton(onClick = {
                                            if (estaEditando) {
                                                AdminSession.listaServicios[index] = ser.copy(precio = precioEditadoTemp)
                                                editandoPrecioDe = null
                                            } else {
                                                editandoPrecioDe = ser.nombre
                                                precioEditadoTemp = ser.precio
                                            }
                                        }) {
                                            Icon(if (estaEditando) Icons.Default.Save else Icons.Default.Edit, null, tint = if(estaEditando) Color(0xFF2E7D32) else Color.Gray)
                                        }
                                    }
                                }

                                if (estaEditando) {
                                    OutlinedTextField(
                                        value = precioEditadoTemp,
                                        onValueChange = { precioEditadoTemp = it },
                                        label = { Text("Editar Precio") },
                                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                                    )
                                } else {
                                    Text(ser.precio, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2E7D32), modifier = Modifier.align(Alignment.End))
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // BOTÓN ELIMINAR
            if (!mostrarFormulario && editandoPrecioDe == null) {
                Button(
                    onClick = {
                        if (!modoEliminar) modoEliminar = true
                        else if (servicioSeleccionado != null) {
                            AdminSession.listaServicios.removeIf { it.nombre == servicioSeleccionado }
                            modoEliminar = false; servicioSeleccionado = null
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp).height(50.dp),
                    colors = ButtonDefaults.buttonColors(if (servicioSeleccionado != null) Color.Black else rojoSuperior),
                    enabled = AdminSession.listaServicios.isNotEmpty()
                ) {
                    Text(if (servicioSeleccionado != null) "CONFIRMAR ELIMINAR" else if (modoEliminar) "SELECCIONE UNO" else "ELIMINAR SERVICIO")
                }
            }
        }
    }
}