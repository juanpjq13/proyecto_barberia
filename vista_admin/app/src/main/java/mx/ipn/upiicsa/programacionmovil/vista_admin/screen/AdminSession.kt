package mx.ipn.upiicsa.programacionmovil.vista_admin

import androidx.compose.runtime.mutableStateListOf

// MODELOS DE DATOS
data class OpcionMenu(val id: Int, val titulo: String, val ruta: String)
data class Sucursal(val nombre: String, val direccion: String)

data class Prestador(
    val nombre: String,
    val sucursalAsignada: String,
    val correo: String
)

data class Promocion(
    val titulo: String,
    val descripcion: String,
    val vigencia: String
)

data class Servicio(
    val nombre: String,
    val duracion: String,
    var precio: String
)

// SESIÓN GLOBAL DEL ADMINISTRADOR
object AdminSession {
    const val ADMIN_EMAIL = "admin@barberia.com"
    const val ADMIN_PASSWORD = "admin123"

    val funcionalidades = listOf(
        OpcionMenu(1, "Gestionar sucursales", "gestion_sucursales"),
        OpcionMenu(2, "Gestionar prestadores de servicio", "gestion_prestadores"),
        OpcionMenu(3, "Gestionar promociones", "gestion_promociones"),
        OpcionMenu(4, "Gestionar servicios y precios", "gestion_servicios") // Fusionado
    )

    val listaSucursales = mutableStateListOf(
        Sucursal("Sucursal Centro", "Av. Principal #123, Col. Centro"),
        Sucursal("Sucursal Norte", "Calle Galaxia #45, Plaza Norte")
    )

    val listaPrestadores = mutableStateListOf(
        Prestador("Juan Pérez", "Sucursal Centro", "juan.perez@barberia.com"),
        Prestador("Carlos Ruiz", "Sucursal Norte", "carlos.ruiz@barberia.com")
    )

    val listaPromociones = mutableStateListOf(
        Promocion("Buen Fin Barber", "20% de descuento en todos los servicios", "30/11/2025"),
        Promocion("Jueves de Barba", "2x1 en arreglo de barba", "Cada jueves")
    )

    // LISTA CORREGIDA: Sin el símbolo '<' y con los 3 parámetros requeridos
    val listaServicios = mutableStateListOf(
        Servicio("Corte de cabello", "30 min", "$180 - $350 MXN"),
        Servicio("Recorte o afeitado de barba", "30 min", "$150 - $280 MXN"),
        Servicio("Masaje o tratamiento capilar", "30 min", "$200 - $450 MXN")
    )
}