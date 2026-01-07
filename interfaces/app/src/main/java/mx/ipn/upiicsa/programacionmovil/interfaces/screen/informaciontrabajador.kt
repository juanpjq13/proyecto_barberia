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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.ipn.upiicsa.programacionmovil.interfaces.R

@Composable
fun InformacionTrabajadorScreen() {
    val rojoSuperior = Color(0xFFD44144)
    val azulFondo = Color(0xFFCFDDFF)
    val colorElipse = Color(0xFFCFDDFF)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(azulFondo)
    ) {
        //  FONDO ROJO SUPERIOR
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

        //  LOGO TRANSLÚCIDO (marca de agua)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de fondo",
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-200).dp)
                .alpha(0.05f),
            contentScale = ContentScale.Fit
        )

        //  Elipses superiores (decorativas)
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

        // CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  Cuadro con la foto del trabajador
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color.White, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // 📸 Imagen del trabajador
                    contentDescription = "Foto del trabajador",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(Color.Transparent, RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre del trabajador
            Text(
                text = "Nombre del trabajador",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ️ Tipos de corte que maneja
            Text(
                text = "Tipos de corte que maneja",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            //  Cuadro grande para los trabajos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .background(Color(0xFFE9EFFD), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Aquí se mostrarán las imágenes de los trabajos
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(azulFondo, RoundedCornerShape(16.dp))
                    )
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(azulFondo, RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInformacionTrabajador() {
    InformacionTrabajadorScreen()
}
