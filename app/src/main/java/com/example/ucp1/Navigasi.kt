package com.example.ucp1

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp1.view.Page1
import com.example.ucp1.view.Page2

enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun DataApp() {
    val navController = rememberNavController()

    var nama by remember { mutableStateOf("") }
    var npwp by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenisPajak by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Navigasi.Formulir.name
    ) {
        composable(Navigasi.Formulir.name) {
            Page1(
                onSubmit = { inputNama, inputNpwp, inputAlamat,
                             inputJenis, inputTanggal, inputStatus ->
                    nama = inputNama
                    npwp = inputNpwp
                    alamat = inputAlamat
                    jenisPajak = inputJenis
                    tanggal = inputTanggal
                    status = inputStatus

                    navController.navigate(Navigasi.Detail.name)
                }
            )
        }

        composable(Navigasi.Detail.name) {
            Page2(
                nama = nama,
                npwp = npwp,
                alamat = alamat,
                jenisPajak = jenisPajak,
                tanggal = tanggal,
                status = status,
                onBack = { navController.popBackStack() }
            )
        }
    }
}