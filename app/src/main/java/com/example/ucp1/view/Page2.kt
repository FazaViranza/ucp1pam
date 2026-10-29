package com.example.taxoffice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

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
    val paddingScreen =
        dimensionResource(R.dimens.padding_screen)
    val spacingMedium =
        dimensionResource(R.dimens.spacing_medium)

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

        DataRow(
            label = stringResource(R.string.label_nama),
            value = nama
        )

        DataRow(
            label = stringResource(R.string.label_npwp),
            value = npwp
        )

        DataRow(
            label = stringResource(R.string.label_alamat),
            value = alamat
        )

        DataRow(
            label = stringResource(R.string.label_jenis_pajak),
            value = jenisPajak
        )

        DataRow(
            label = stringResource(R.string.label_tanggal),
            value = tanggal
        )

        DataRow(
            label = stringResource(R.string.status_pembayaran),
            value = status
        )

        Spacer(
            modifier = Modifier.height(
                dimensionResource(R.dimen.spacing_small)
            )
        )

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
            dimensionResource(R.dimens.spacing_small)
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )

        Text(
            text = value.ifBlank {
                stringResource(R.string.data_tidak_tersedia)
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}