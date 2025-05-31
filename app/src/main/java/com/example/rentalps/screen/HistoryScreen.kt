package com.example.rentalps.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rentalps.data.FakeDatabase

@Composable
fun HistoryScreen() {
    val bookings = remember { FakeDatabase.getBookings() }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(bookings) { booking ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("PS: ${booking.title }", style = MaterialTheme.typography.titleMedium)
                    Text("Tanggal: ${booking.bookingDate}")
                    Text("Jam: ${booking.playTime}")
                    Text("Status: ${booking.status}")
                }
            }
        }
    }
}