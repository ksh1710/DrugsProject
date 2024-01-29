package com.example.drugsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.drugsproject.ui.theme.poppinsFontFamily


@OptIn(ExperimentalMaterialApi::class)
//@Preview
@Composable
fun OtherScreen( navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Text(
            fontFamily = poppinsFontFamily,
            text = stringResource(R.string.relax_yourself_with_yoga_and_meditation),
            modifier = Modifier
                .background(Color.White),
            color = Color.Black,
            fontSize = 25.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Meditation",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = poppinsFontFamily
            )
            Button(
                onClick = {
                          navController.navigate("music")
//                    val nav = Intent(context, MusicPlayerActivity::class.java)
//                    startActivity(context, nav, null)
                },
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
            ) {
                Text(text = "Go there", color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "", tint = Color.Black)

            }
        }
    }
}
