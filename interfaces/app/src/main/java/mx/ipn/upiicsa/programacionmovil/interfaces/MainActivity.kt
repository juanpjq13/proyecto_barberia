package mx.ipn.upiicsa.programacionmovil.interfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// IMPORTANTE: Asegúrate de que estos nombres coincidan con tus archivos .kt
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.AgendarCitaScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.InicioScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.CrearCuentaScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.LoginScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.cambiarcontraseñaScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.InicioconcitasScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.InicioSinCitasScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.RevisarCitasScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.NuestroEquipoScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.NuestrosTrabajosScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.screen.InformacionUsuarioScreen
import mx.ipn.upiicsa.programacionmovil.interfaces.ui.theme.InterfacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfacesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio",
        modifier = modifier
    ) {
        composable("inicio") { InicioScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("registro") { CrearCuentaScreen(navController) }
        composable("cambiarcontraseña") { cambiarcontraseñaScreen(navController) }
        composable("iniciosincitas") { InicioSinCitasScreen(navController) }
        composable("inicioconcitas") { InicioconcitasScreen(navController) }
        composable("agendarcita") { AgendarCitaScreen(navController) }
        composable("nuestroequipo") { NuestroEquipoScreen(navController) }
        composable("nuestrostrabajos") { NuestrosTrabajosScreen(navController) }
        composable("informacionusuario") { InformacionUsuarioScreen(navController) }

        // NUEVA RUTA: Revisar Citas
        composable("revisarcitas") { RevisarCitasScreen(navController) }
    }
}