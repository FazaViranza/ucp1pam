package com.example.taxoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.taxoffice.ui.theme.TaxOfficeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaxOfficeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DataApp()
                }
            }
        }
    }
}