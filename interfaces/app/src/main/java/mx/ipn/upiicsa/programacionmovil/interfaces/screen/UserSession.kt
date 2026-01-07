package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.runtime.mutableStateListOf

// Modelo para el Usuario
data class Usuario(
    val nombre: String,
    val correo: String,
    val contrasena: String
)

// Modelo para la Cita
data class Cita(
    val sucursal: String, // NUEVO CAMPO
    val fecha: String,
    val hora: String,
    val servicio: String
)


object UserSession {
    // 1. Mapa de todos los usuarios registrados (para validación de login)
    val usuariosRegistrados = mutableMapOf<String, Usuario>()

    // 2. CRÍTICO: El usuario que tiene la sesión abierta actualmente
    // Esto lo usará la pantalla InformacionUsuarioScreen
    var usuarioActual: Usuario? = null

    // 3. La cita que el usuario acaba de agendar
    var citaActual: Cita? = null

    // 4. Lista global de citas (necesaria para la pantalla de Revisar Citas)
    val listaCitas = mutableStateListOf<Cita>()

    init {
        // Usuario de prueba para que siempre puedas testear el login
        val admin = Usuario("Administrador", "admin@mail.com", "1234")
        usuariosRegistrados["admin@mail.com"] = admin

        // Descomenta la línea de abajo si quieres que inicie con sesión abierta para pruebas
        // usuarioActual = admin
    }
}