package com.lelestacia.lelenime

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lelestacia.lelenime.core.common.theme.LelenimeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize: WindowSizeClass = calculateWindowSizeClass(activity = this)
            val uiController: SystemUiController = rememberSystemUiController()
            val bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator()
            val navController: NavHostController =
                rememberAnimatedNavController(bottomSheetNavigator)
            val activityVM by viewModels<ActivityViewModel>()

            //  Theme based on int
            val theme: Int by activityVM.darkModePreferences.collectAsStateWithLifecycle()

            //  Dark Icon / Light Icon
            val darkIcons: Boolean =
                if (isSystemInDarkTheme()) {
                    theme == 1
                } else {
                    theme != 2
                }

            //  Dynamic Color Preferences
            val dynamicModePreferences: Boolean =
                if (Build.VERSION.SDK_INT <= 30) {
                    false
                } else {
                    activityVM.dynamicMode.collectAsStateWithLifecycle().value
                }

            LelenimeTheme(
                darkTheme = when (theme) {
                    1 -> false
                    2 -> true
                    else -> isSystemInDarkTheme()
                },
                dynamicColor = dynamicModePreferences,
                content = {
                    Surface {
                        LelenimeApplication(
                            windowSize = windowSize,
                            navController = navController,
                            bottomSheetNavigator = bottomSheetNavigator,
                            uiController = uiController,
                            darkIcons = darkIcons
                        )
                    }
                }
            )
        }
    }
}
