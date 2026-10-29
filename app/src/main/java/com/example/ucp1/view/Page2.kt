package com.example.ucp1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ucp1.R

@Composable
fun Page2(
    nama: String,
    npwp: String,
    alamat: String,
    jenisPajak: String,
    tanggal: String,
    status: String,
    onBack: () -> Unit
) {
    val paddingScreen = dimensionResource(R.dimen.padding_screen)
    val spacingMedium = dimensionResource(R.dimen.spacing_medium)
    val spacingSmall = dimensionResource(R.dimen.spacing_small)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingScreen),
        verticalArrangement = Arrangement.spacedBy(spacingMedium)
    ) {
        Text(
            text = stringResource(R.string.page_detail_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        HorizontalDivider()

        DataRow(stringResource(R.string.label_nama), nama)
        DataRow(stringResource(R.string.label_npwp), npwp)
        DataRow(stringResource(R.string.label_alamat), alamat)
        DataRow(stringResource(R.string.label_jenis_pajak), jenisPajak)
        DataRow(stringResource(R.string.label_tanggal), tanggal)
        DataRow(stringResource(R.string.status_pembayaran), status)

        Spacer(modifier = Modifier.height(spacingSmall))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.button_kembali))
        }
    }
}

@Composable
private fun DataRow(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.spacing_small)
        )
    ) {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Text(
            text = value.ifBlank {
                stringResource(R.string.data_tidak_tersedia)
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}