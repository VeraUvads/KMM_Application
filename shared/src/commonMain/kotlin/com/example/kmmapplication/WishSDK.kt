package com.example.kmmapplication

import com.example.kmmapplication.cache.Database
import com.example.kmmapplication.data.Wish
import com.example.kmmapplication.network.WishApi
import com.example.kmmapplication.utils.DatabaseDriverFactory

class WishSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = WishApi()

    @Throws(Exception::class)
    suspend fun getWishes(forceReload: Boolean = false): List<Wish> {
        val cachedWishes = database.getAllWishes()
        return if (cachedWishes.isNotEmpty() && !forceReload) {
            cachedWishes
        } else {
            api.getAllWished().also {
                database.clearDatabase()
                database.createWishes(it)
            }
        }
    }

    suspend fun removeWish(id: Long) {
         database.deleteWish(id)
    }

    suspend fun insertWish(wish: Wish) {
        database.insertWish(wish)
    }
}
