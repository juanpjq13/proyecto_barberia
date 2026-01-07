package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(navController: NavHostController) {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFE9EFFD)
    val rojoBoton = Color(0xFFC76B6C)
    val azulContenedor = Color(0xFFDDE6FE)

    // Estados para Fecha
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    val fechaSeleccionada = remember(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.format(Date(it))
        } ?: "Seleccionar Fecha"
    }

    // Estados para Sucursal y Servicio
    val sucursales = listOf("Sucursal Centro", "Sucursal Norte")
    var sucursalSeleccionada by remember { mutableStateOf("Seleccionar Barbería") }
    var expandedSucursal by remember { mutableStateOf(false) }

    val servicios = listOf("Corte de cabello", "Recorte o afeitado de barba", "Masaje capilar")
    var servicioSeleccionado by remember { mutableStateOf("Seleccionar Servicio") }
    var expandedServicio by remember { mutableStateOf(false) }

    // Estado para Horario
    var horarioSeleccionado by remember { mutableStateOf("") }
    val slotsHora = listOf(
        "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM",
        "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM",
        "8:00 PM", "8:30 PM", "9:00 PM", "9:30 PM"
    )

    Box(modifier = Modifier.fillMaxSize().background(azulFondo)) {
        // Fondo con arco rojo
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = rojoSuperior.copy(alpha = 0.6f),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(-size.width * 0.3f, -size.height * 0.5f),
                size = androidx.compose.ui.geometry.Size(size.width * 1.6f, size.height)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text(text = "Agendar Cita", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = azulContenedor),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    // SELECTOR DE SUCURSAL
                    Box {
                        OutlinedCard(
                            onClick = { expandedSucursal = true },
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
                        ) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(id = android.R.drawable.ic_menu_directions), contentDescription = null, tint = rojoBoton, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = sucursalSeleccionada, modifier = Modifier.weight(1f), fontSize = 14.sp)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        }
                        DropdownMenu(expanded = expandedSucursal, onDismissRequest = { expandedSucursal = false }, modifier = Modifier.fillMaxWidth(0.7f)) {
                            sucursales.forEach { s ->
                                DropdownMenuItem(text = { Text(s) }, onClick = {
                                    sucursalSeleccionada = s
                                    expandedSucursal = false
                                })
                            }
                        }
                    }

                    // SELECTOR DE FECHA
                    OutlinedCard(
                        onClick = { showDatePicker = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Fecha: $fechaSeleccionada", modifier = Modifier.weight(1f), fontSize = 14.sp)
                            Icon(painter = painterResource(id = android.R.drawable.ic_menu_today), contentDescription = null, modifier = Modifier.size(20.dp))
                        }
                    }

                    // SELECTOR DE SERVICIO
                    Box {
                        OutlinedCard(
                            onClick = { expandedServicio = true },
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
                        ) {
                            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = servicioSeleccionado, modifier = Modifier.weight(1f), fontSize = 14.sp)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        }
                        DropdownMenu(expanded = expandedServicio, onDismissRequest = { expandedServicio = false }, modifier = Modifier.fillMaxWidth(0.7f)) {
                            servicios.forEach { s ->
                                DropdownMenuItem(text = { Text(s) }, onClick = {
                                    servicioSeleccionado = s
                                    expandedServicio = false
                                })
                            }
                        }
                    }

                    Text("Horarios disponibles:", fontWeight = FontWeight.Bold, fontSize = 14.sp)

                    // AQUÍ ESTÁN LOS HORARIOS (LazyVerticalGrid corregido)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.height(200.dp), // Altura fija para que scroll funcione
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(slotsHora) { hora ->
                            val isSelected = horarioSeleccionado == hora
                            Card(
                                onClick = { horarioSeleccionado = hora },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) rojoBoton else Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.height(45.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        text = hora,
                                        fontSize = 12.sp,
                                        color = if (isSelected) Color.White else Color.Black
                                    )
                                }
                            }
                        }
                    }

                    // BOTÓN DE CONFIRMACIÓN
                    Button(
                        onClick = {
                            UserSession.citaActual = Cita(
                                sucursal = sucursalSeleccionada,
                                fecha = fechaSeleccionada,
                                hora = horarioSeleccionado,
                                servicio = servicioSeleccionado
                            )
                            navController.navigate("inicioconcitas")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = rojoBoton),
                        modifier = Modifier.fillMaxWidth().height(55.dp),
                        shape = RoundedCornerShape(16.dp),
                        enabled = horarioSeleccionado.isNotEmpty() &&
                                servicioSeleccionado != "Seleccionar Servicio" &&
                                sucursalSeleccionada != "Seleccionar Barbería" &&
                                fechaSeleccionada != "Seleccionar Fecha"
                    ) {
                        Text("Confirmar Cita", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Botón de Volver
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.statusBarsPadding().padding(16.dp).align(Alignment.TopStart).zIndex(1f)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
        }

        // Diálogo del DatePicker
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showDatePicker = false }) { Text("OK") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}