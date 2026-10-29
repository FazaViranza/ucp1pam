package com.example.ucp1.view

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ucp1.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun Page1(
    onSubmit: (
        String, String, String, String, String, String
    ) -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var npwp by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenisPajak by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val paddingScreen = dimensionResource(R.dimen.padding_screen)
    val spacingSmall = dimensionResource(R.dimen.spacing_small)
    val spacingMedium = dimensionResource(R.dimen.spacing_medium)

    val formValid = nama.isNotBlank() &&
        npwp.isNotBlank() &&
        alamat.isNotBlank() &&
        jenisPajak.isNotBlank() &&
        tanggal.isNotBlank() &&
        status.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingScreen),
        verticalArrangement = Arrangement.spacedBy(spacingMedium)
    ) {
        Text(
            text = stringResource(R.string.page_title),
            fontSize = dimensionResource(R.dimen.text_title).value.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.tax_primary)
        )

        Text(
            text = stringResource(R.string.page_subtitle),
            fontSize = dimensionResource(R.dimen.text_subtitle).value.sp
        )

        HorizontalDivider()

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it; error = false },
            label = { Text(stringResource(R.string.label_nama)) },
            placeholder = { Text(stringResource(R.string.hint_nama)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = npwp,
            onValueChange = { npwp = it; error = false },
            label = { Text(stringResource(R.string.label_npwp)) },
            placeholder = { Text(stringResource(R.string.hint_npwp)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = alamat,
            onValueChange = { alamat = it; error = false },
            label = { Text(stringResource(R.string.label_alamat)) },
            placeholder = { Text(stringResource(R.string.hint_alamat)) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacingSmall),
            verticalAlignment = Alignment.Top
        ) {
            JenisPajakDropdown(
                selected = jenisPajak,
                onSelected = { jenisPajak = it; error = false },
                modifier = Modifier.weight(1f)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.label_tanggal),
                    fontSize = dimensionResource(R.dimen.text_body).value.sp
                )

                Spacer(modifier = Modifier.height(spacingSmall))

                OutlinedButton(
                    onClick = {
                        val calendar = Calendar.getInstance()
                        DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                val selected = Calendar.getInstance()
                                selected.set(year, month, day)
                                tanggal = SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                ).format(selected.time)
                                error = false
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = tanggal.ifBlank {
                            stringResource(R.string.pilih_tanggal)
                        }
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.status_pembayaran),
            fontSize = dimensionResource(R.dimen.text_body).value.sp,
            fontWeight = FontWeight.Medium
        )

        val statuses = listOf(
            R.string.status_diproses,
            R.string.status_selesai,
            R.string.status_ditunda
        )

        statuses.forEach { statusRes ->
            val label = stringResource(statusRes)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = status == label,
                    onClick = { status = label; error = false }
                )
                Text(text = label)
            }
        }

        if (error) {
            Text(
                text = stringResource(R.string.error_form),
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(spacingSmall))

        Button(
            onClick = {
                if (formValid) {
                    onSubmit(nama, npwp, alamat, jenisPajak, tanggal, status)
                } else {
                    error = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.button_height))
        ) {
            Text(text = stringResource(R.string.button_submit))
        }
    }
}

@Composable
private fun JenisPajakDropdown(
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val options = listOf(
        stringResource(R.string.pajak_pph),
        stringResource(R.string.pajak_ppn),
        stringResource(R.string.pajak_pbb)
    )

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.label_jenis_pajak),
            fontSize = dimensionResource(R.dimen.text_body).value.sp
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selected.ifBlank {
                        stringResource(R.string.pilih_jenis_pajak)
                    }
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}