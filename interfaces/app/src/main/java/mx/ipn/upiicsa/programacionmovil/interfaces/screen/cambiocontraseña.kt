package mx.ipn.upiicsa.programacionmovil.interfaces.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.ipn.upiicsa.programacionmovil.interfaces.R

@Composable
fun cambiarcontrasenaScreen() {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFE9EFFD)
    val rojoBoton = Color(0xFFC76B6C)
    val colorElipse = Color(0xFFE9EFFD)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFondo)
    ) {
        //  Fondo rojo superior
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

        //  Logo translúcido
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de fondo",
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-200).dp)
                .alpha(0.05f),
            contentScale = ContentScale.Fit
        )

        //  Elipses decorativas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .offset(x = (-190).dp, y = (-40).dp)
                    .size(110.dp)
                    .background(colorElipse, CircleShape)
            )

            Box(
                modifier = Modifier
                    .offset(x = (190).dp, y = (-40).dp)
                    .size(110.dp)
                    .background(colorElipse, CircleShape)
            )
        }

        //  Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  Ícono del usuario
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Ícono usuario",
                    modifier = Modifier.size(70.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            //  Recuadro central
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFDCE8FF), RoundedCornerShape(24.dp))
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Campos de contraseña
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Contraseña actual") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(16.dp))
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Nueva contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(16.dp))
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Confirmar contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    //  Botón “Cambiar contraseña”
                    Button(
                        onClick = { /* Acción cambiar contraseña */ },
                        colors = ButtonDefaults.buttonColors(containerColor = rojoBoton),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text("Cambiar contraseña", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previewcambiarcontrasena() {
    cambiarcontrasenaScreen()
}
