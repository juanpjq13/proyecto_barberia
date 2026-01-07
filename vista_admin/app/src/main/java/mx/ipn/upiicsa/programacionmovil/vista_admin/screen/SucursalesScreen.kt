package mx.ipn.upiicsa.programacionmovil.vista_admin

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
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
fun SucursalesScreen(navController: NavHostController) {
    val azulFondo = Color(0xFFCFDDFF)
    val rojoSuperior = Color(0xFFD44144)

    var modoEliminar by remember { mutableStateOf(false) }
    var sucursalSeleccionada by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        // ... (Canvas del arco rojo se mantiene igual)
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (modoEliminar) { modoEliminar = false; sucursalSeleccionada = null }
                    else { navController.popBackStack() }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Text(
                    text = if (modoEliminar) "SELECCIONE PARA ELIMINAR" else "GESTIONAR SUCURSALES",
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(160.dp))

            // LISTA DINÁMICA: Ahora lee directamente de la lista mutable
            AdminSession.listaSucursales.forEach { sucursal ->
                val esSeleccionada = sucursalSeleccionada == sucursal.nombre

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable(enabled = modoEliminar) { sucursalSeleccionada = sucursal.nombre },
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (esSeleccionada) Color(0xFFFFEBEE) else Color.White
                    ),
                    border = if (esSeleccionada) BorderStroke(2.dp, rojoSuperior) else null,
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (esSeleccionada) Icons.Default.CheckCircle else Icons.Default.LocationOn,
                            contentDescription = null, tint = rojoSuperior, modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(sucursal.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(sucursal.direccion, fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (!modoEliminar) {
                        modoEliminar = true
                    } else if (sucursalSeleccionada != null) {
                        // LÓGICA DE ELIMINACIÓN DEFINITIVA
                        AdminSession.listaSucursales.removeIf { it.nombre == sucursalSeleccionada }

                        // Resetear estados
                        modoEliminar = false
                        sucursalSeleccionada = null
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sucursalSeleccionada != null) Color.Black else rojoSuperior
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(bottom = 30.dp).fillMaxWidth(0.8f).height(50.dp),
                enabled = !modoEliminar || sucursalSeleccionada != null
            ) {
                Text(
                    text = when {
                        sucursalSeleccionada != null -> "CONFIRMAR ELIMINACIÓN"
                        modoEliminar -> "SELECCIONE UNA"
                        else -> "ELIMINAR SUCURSAL"
                    },
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}