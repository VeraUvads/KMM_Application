package com.example.kmmapplication.wishes

import com.example.kmmapplication.data.Wish
import com.example.kmmapplication.utils.settings
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface WishStorage {
    suspend fun saveWish(wish: Wish)
    suspend fun getWishes(): List<Wish>
    suspend fun removeWish(wish: Wish)
}

class WishStorageIml(private val setting: FlowSettings = settings()) : WishStorage {
    companion object {
        private const val WISHES_KEY = "WISHES_KEY"
    }

    override suspend fun saveWish(wish: Wish) {
        val currentWishesSerialized = setting.getStringOrNull(WISHES_KEY)
        val currentWishes: MutableSet<Wish> = if (currentWishesSerialized == null) {
            mutableSetOf()
        } else {
            Json.decodeFromString(currentWishesSerialized)
        }
        val result = currentWishes.plus(wish)

        val resultSerialized = Json.encodeToString(result)

        setting.putString(WISHES_KEY, resultSerialized)
    }

    override suspend fun getWishes(): List<Wish> {
        val currentWishesSerialized = setting.getStringOrNull(WISHES_KEY)
        val currentWishes: List<Wish> = if (currentWishesSerialized == null) {
            listOf()
        } else {
            Json.decodeFromString(currentWishesSerialized)
        }
        return currentWishes
    }

    override suspend fun removeWish(wish: Wish) {
        val currentWishesSerialized = setting.getStringOrNull(WISHES_KEY)
        val currentWishes: MutableSet<Wish> = if (currentWishesSerialized == null) {
            mutableSetOf()
        } else {
            Json.decodeFromString(currentWishesSerialized)
        }
        val result = currentWishes.minus(wish)

        val resultSerialized = Json.encodeToString(result)

        setting.putString(WISHES_KEY, resultSerialized)
    }

//    override suspend fun saveWish(wishes: List<Wish>) {
//        val resultSerialized = Json.encodeToString(wishes)
//        setting.putString(WISHES_KEY, resultSerialized)
//    }
//
//    override fun removeWish() {
//        val wishesList = setting.getStringOrNull(WISHES_KEY)
//    }
}
