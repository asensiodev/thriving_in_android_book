package com.asensiodev.whatspackt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.asensiodev.whatspackt.ui.navigation.MainNavigation
import com.asensiodev.whatspackt.ui.theme.WhatsPacktTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
            WhatsPacktTheme {
                val navHostController = rememberNavController()
                MainNavigation(navController = navHostController)
            }
        }
    }
}