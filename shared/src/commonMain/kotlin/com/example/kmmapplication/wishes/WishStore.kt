package com.example.kmmapplication.wishes

import com.example.kmmapplication.WishSDK
import com.example.kmmapplication.data.Wish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

interface WishStore {
    val wishes: Flow<List<Wish>>

    suspend fun update()

    suspend fun removeWish(id: Long)

    suspend fun insertWish(wishText: String)
}

class WishStoreImpl(private val sdk: WishSDK) : WishStore {
    override val wishes = MutableStateFlow(listOf<Wish>())

    override suspend fun update() {
        val sdkWishes = sdk.getWishes()
        wishes.value = sdkWishes
        if (sdkWishes.isEmpty()) {
            insertWish("TEST")
            insertWish("TEST2")
        }
    }

    override suspend fun removeWish(id: Long) {
        sdk.removeWish(id)
        update()
    }

    override suspend fun insertWish(wishText: String) {
        val id = Random.nextLong()
        val wish = Wish(id = id, text = wishText + id)
        sdk.insertWish(wish)
        update()
    }
}
