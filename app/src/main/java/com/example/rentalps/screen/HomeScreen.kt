package com.example.rentalps.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.net.Uri
import com.example.rentalps.R

data class PlayStationItem(
    val title: String,
    val pricePerHour: String,
    val imageResId: Int
)

@Composable
fun HomeScreen(navController: NavController) {
    val psList = listOf(
        PlayStationItem("PlayStation 5", "Rp 15.000 / jam", R.drawable.ps5),
        PlayStationItem("PlayStation 4", "Rp 12.000 / jam", R.drawable.ps4),
        PlayStationItem("PlayStation 3", "Rp 10.000 / jam", R.drawable.ps3)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(psList) { item ->
            PSCard(item) {
                val encodedTitle = Uri.encode(item.title)
                val encodedPrice = Uri.encode(item.pricePerHour)
                navController.navigate("booking/$encodedTitle/$encodedPrice")
            }
        }
    }
}

@Composable
fun PSCard(item: PlayStationItem, onOrderClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(item.pricePerHour, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onOrderClick, modifier = Modifier.fillMaxWidth()) {
                Text("Sewa Sekarang")
            }
        }
    }
}