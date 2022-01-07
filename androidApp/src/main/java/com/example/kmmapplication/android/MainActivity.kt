package com.example.kmmapplication.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kmmapplication.android.main.WishesUI
import com.example.kmmapplication.utils.DatabaseDriverFactory
import com.example.kmmapplication.wishes.WishesViewModel

class MainActivity : AppCompatActivity() {

    private val wishesViewModel by viewModels<WishesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wishesViewModel.getWishes(DatabaseDriverFactory(applicationContext))
        setContent {
            WishesUI(wishesViewModel)
        }
    }
}
