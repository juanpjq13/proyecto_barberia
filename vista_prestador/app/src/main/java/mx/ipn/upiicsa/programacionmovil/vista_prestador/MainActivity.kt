package mx.ipn.upiicsa.programacionmovil.vista_prestador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginPrestadorScreen(navController) }
                composable("agenda") { AgendaScreen(navController) }
                composable(
                    "detalle/{citaId}",
                    arguments = listOf(navArgument("citaId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("citaId") ?: 0
                    DetalleCitaScreen(navController, id)
                }
            }
        }
    }
}