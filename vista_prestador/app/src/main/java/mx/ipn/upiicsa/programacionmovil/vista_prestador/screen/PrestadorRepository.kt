package mx.ipn.upiicsa.programacionmovil.vista_prestador

import androidx.compose.runtime.mutableStateListOf

// Estructura de la cita
data class Cita(
    val id: Int,
    val cliente: String,
    val servicio: String,
    val hora: String,
    val telefono: String,
    val notas: String
)

object PrestadorRepository {
    // Credenciales predefinidas para el Staff
    const val PRESTADOR_USER = "barbero1@barberia.com"
    const val PRESTADOR_PASS = "12345"

    // Datos de ejemplo para la agenda
    val listaCitas = mutableStateListOf(
        Cita(1, "Carlos Ruíz", "Corte de cabello", "10:00 AM", "5512345678", "Corte tipo skin fade."),
        Cita(2, "Pedro Soto", "Afeitado de barba", "11:30 AM", "5587654321", "Barba marcada con navaja."),
        Cita(3, "Mauricio Islas", "Corte + Barba", "01:00 PM", "5599887766", "Cliente frecuente.")
    )
}