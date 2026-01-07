package mx.ipn.upiicsa.programacionmovil.vista_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ipn.upiicsa.programacionmovil.vista_admin.ui.theme.Vista_adminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vista_adminTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login_admin"
                ) {
                    composable("login_admin") {
                        LoginAdminScreen(navController)
                    }
                    composable("inicio_admin") {
                        InicioAdminScreen(navController)
                    }
                    composable("gestion_sucursales") {
                        SucursalesScreen(navController)
                    }
                    composable("gestion_prestadores") {
                        PrestadoresScreen(navController)
                    }
                    composable("gestion_promociones") {
                        PromocionesScreen(navController)
                    }
                    composable("gestion_servicios") {
                        ServiciosPreciosScreen(navController) }
                }
            }
        }
    }
}