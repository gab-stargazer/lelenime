package com.lelestacia.lelenime.feature.more.screen.more

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lelestacia.lelenime.core.common.Constant
import com.lelestacia.lelenime.core.common.route.Screen
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import com.lelestacia.lelenime.feature.more.component.MoreScreenButton

@Composable
fun MoreScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        MoreScreenButton(
            text = "Settings",
            icon = Icons.Filled.Settings
        ) {
            navController.navigate(route = Screen.Settings.route) {
                restoreState = true
            }
        }
        MoreScreenButton(
            text = "About",
            icon = Icons.Filled.Info,
            onClick = {
                navController.navigate(route = Screen.About.route) {
                    restoreState = true
                }
            }
        )
        MoreScreenButton(
            text = "Privacy Policy",
            icon = Icons.Filled.Shield,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(Constant.PRIVACY_POLICY)
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoreScreen() {
    LelenimeTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Surface {
            MoreScreen(navController = rememberNavController())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoreScreenDarkMode() {
    LelenimeTheme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Surface {
            MoreScreen(navController = rememberNavController())
        }
    }
}