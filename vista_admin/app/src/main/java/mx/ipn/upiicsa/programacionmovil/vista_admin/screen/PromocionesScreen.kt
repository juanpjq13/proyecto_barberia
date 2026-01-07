package mx.ipn.upiicsa.programacionmovil.vista_admin

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
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
fun PromocionesScreen(navController: NavHostController) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)

    // Estados eliminación
    var modoEliminar by remember { mutableStateOf(false) }
    var promoSeleccionada by remember { mutableStateOf<String?>(null) }

    // Estados añadir
    var mostrarFormulario by remember { mutableStateOf(false) }
    var nuevoTitulo by remember { mutableStateOf("") }
    var nuevaDesc by remember { mutableStateOf("") }
    var nuevaVigencia by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        // Arco decorativo
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
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    if (modoEliminar || mostrarFormulario) {
                        modoEliminar = false
                        mostrarFormulario = false
                        promoSeleccionada = null
                    } else {
                        navController.popBackStack()
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }

                Text(
                    text = if (mostrarFormulario) "NUEVA PROMOCIÓN" else "GESTIONAR PROMOS",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(onClick = {
                    mostrarFormulario = !mostrarFormulario
                    modoEliminar = false
                    promoSeleccionada = null
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(140.dp))

            if (mostrarFormulario) {
                // Formulario para añadir
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(value = nuevoTitulo, onValueChange = { nuevoTitulo = it }, label = { Text("Título de la promo") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = nuevaDesc, onValueChange = { nuevaDesc = it }, label = { Text("Descripción / Descuento") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = nuevaVigencia, onValueChange = { nuevaVigencia = it }, label = { Text("Vigencia (ej. 31/Dic)") }, modifier = Modifier.fillMaxWidth())

                        Button(
                            onClick = {
                                if (nuevoTitulo.isNotBlank()) {
                                    AdminSession.listaPromociones.add(Promocion(nuevoTitulo, nuevaDesc, nuevaVigencia))
                                    nuevoTitulo = ""; nuevaDesc = ""; nuevaVigencia = ""; mostrarFormulario = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Publicar Promoción", color = Color.White)
                        }
                    }
                }
            } else {
                // Lista de promociones
                AdminSession.listaPromociones.forEach { promo ->
                    val esSeleccionada = promoSeleccionada == promo.titulo
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable(enabled = modoEliminar) {
                                promoSeleccionada = if (esSeleccionada) null else promo.titulo
                            },
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(containerColor = if (esSeleccionada) Color(0xFFFFEBEE) else Color.White),
                        border = if (esSeleccionada) BorderStroke(2.dp, rojoSuperior) else null,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (esSeleccionada) Icons.Default.CheckCircle else Icons.Default.Star,
                                contentDescription = null,
                                tint = rojoSuperior,
                                modifier = Modifier.size(35.dp)
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Column {
                                Text(promo.titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(promo.descripcion, fontSize = 13.sp, color = Color.Gray)
                                Text("Vigencia: ${promo.vigencia}", fontSize = 12.sp, color = rojoSuperior, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (!mostrarFormulario) {
                Button(
                    onClick = {
                        if (!modoEliminar) {
                            modoEliminar = true
                        } else if (promoSeleccionada != null) {
                            AdminSession.listaPromociones.removeIf { it.titulo == promoSeleccionada }
                            modoEliminar = false
                            promoSeleccionada = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (promoSeleccionada != null) Color.Black else rojoSuperior
                    ),
                    modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth(0.8f).height(50.dp),
                    enabled = AdminSession.listaPromociones.isNotEmpty() || modoEliminar
                ) {
                    Text(
                        text = if (promoSeleccionada != null) "CONFIRMAR ELIMINACIÓN"
                        else if (modoEliminar) "SELECCIONE UNA"
                        else "ELIMINAR PROMOCIÓN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}