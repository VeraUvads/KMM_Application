package com.example.kmmapplication.android.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kmmapplication.android.R
import com.example.kmmapplication.android.main.theme.ButtonBlue
import com.example.kmmapplication.data.Wish
import com.example.kmmapplication.wishes.WishesViewModel

@Composable
fun WishesUI(wishesViewModel: WishesViewModel) {
    val wishes by wishesViewModel.wishes.collectAsState(emptyList())

    Text(
        text = "Your wish list",
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    LazyColumn {
        items(wishes) { wish ->
            WishCard(wish) {
                wishesViewModel.removeWish(it)
            }
        }
    }
}

@Composable
fun WishCard(wish: Wish, onRemoveClick: (Long) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.width(300.dp) // TODO: 07.01.2022 0dp?
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ButtonBlue)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = wish.text, color = Color.White)
            }
        }
        Column() {
            Image(
                painter = painterResource(R.drawable.ic_delete),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onRemoveClick(wish.id)
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Red)
                    .padding(13.dp),
                contentDescription = null,
            )
        }
    }
}
