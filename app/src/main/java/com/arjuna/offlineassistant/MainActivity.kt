package com.arjuna.offlineassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arjuna.offlineassistant.ui.screens.Home
import com.arjuna.offlineassistant.ui.theme.AIChatBotTheme
import com.arjuna.offlineassistant.ui.theme.LocalAppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AIChatBotTheme {
                val colors = LocalAppColors.current
                Scaffold(modifier = Modifier.fillMaxSize().background(colors.background)) { padding ->
                    Home(padding)
                }
            }
        }
    }
}
