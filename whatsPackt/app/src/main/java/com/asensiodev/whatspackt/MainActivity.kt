package com.asensiodev.whatspackt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.asensiodev.whatspackt.ui.navigation.MainNavigation
import com.asensiodev.whatspackt.ui.theme.WhatsPacktTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsPacktTheme {
                val navHostController = rememberNavController()
                MainNavigation(navController = navHostController)
            }
        }
    }
}