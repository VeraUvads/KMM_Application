package com.example.kmmapplication.wishes

import com.example.kmmapplication.WishSDK
import com.example.kmmapplication.data.Wish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface WishStore {
    val wishes: Flow<List<Wish>>

    suspend fun update()

    suspend fun removeWish(id: Long)

    suspend fun insertWish(wish: Wish)
}

class WishStoreImpl(private val sdk: WishSDK) : WishStore {
    override val wishes = MutableStateFlow(listOf<Wish>())

    override suspend fun update() {
        wishes.value = sdk.getWishes()
    }

    override suspend fun removeWish(id: Long) {
        sdk.removeWish(id)
        update()
    }

    override suspend fun insertWish(wish: Wish) {
        sdk.insertWish(wish)
        update()
    }
}
