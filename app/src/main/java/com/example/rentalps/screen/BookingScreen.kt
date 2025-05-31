package com.example.rentalps.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentalps.data.Booking
import com.example.rentalps.data.FakeDatabase

import java.util.*

@Composable
fun BookingScreen(navController: NavController, title: String, price: String) {
    var bookingDate by remember { mutableStateOf("") }
    var playTime by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Booking $title", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _: DatePicker, year: Int, month: Int, day: Int ->
                    bookingDate = "$day/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(if (bookingDate.isEmpty()) "Pilih Tanggal Booking" else "Tanggal: $bookingDate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = playTime,
            onValueChange = { playTime = it },
            label = { Text("Jam Main (misal: 14:00)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (bookingDate.isBlank() || playTime.isBlank()) {
                    showError = true
                } else {
                    showError = false
                    FakeDatabase.addBooking(
                        Booking(
                            title = title,
                            pricePerHour = price,
                            bookingDate = bookingDate,
                            playTime = playTime,
                            status = "Booking"
                        )
                    )
                    navController.navigate("history") {
                        popUpTo("home") { inclusive = false }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Konfirmasi Sewa")
        }

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Mohon lengkapi tanggal dan jam main",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}