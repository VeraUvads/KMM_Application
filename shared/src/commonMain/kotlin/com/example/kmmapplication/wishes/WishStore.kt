package com.example.kmmapplication.wishes

import com.example.kmmapplication.data.Wish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface WishStore {
    val wishes: Flow<List<Wish>>

    suspend fun start()
}

class WishStoreImpl(
    private val wishesStorage: WishStorage = WishStorageIml(),
) : WishStore {

    override val wishes = MutableStateFlow(listOf<Wish>())

    override suspend fun start() {
        wishes.value = wishesStorage.wishes
    }
}
