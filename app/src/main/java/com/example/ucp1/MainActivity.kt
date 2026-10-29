package com.example.ucp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ucp1.ui.theme.Ucp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ucp1Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DataApp()
                }
            }
        }
    }
}