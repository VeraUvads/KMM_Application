package com.example.kmmapplication.cache

import com.example.kmmapplication.data.Wish
import com.example.kmmapplication.shared.cache.AppDatabase
import com.example.kmmapplication.utils.DatabaseDriverFactory

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllWishes()
        }
    }

    internal fun getAllWishes(): List<Wish> {
        return dbQuery.selectAllWishesInfo(::mapWishesSelecting).executeAsList()
    }

    private fun mapWishesSelecting(
        id: Long,
        text: String,
    ): Wish {
        return Wish(
            id = id,
            text = text,
        )
    }

    internal fun createWishes(wishes: List<Wish>) {
        dbQuery.transaction {
            wishes.forEach { wish ->
                insertWish(wish)
            }
        }
    }

    fun insertWish(wish: Wish) {
        dbQuery.insertWish(
            id = wish.id,
            text = wish.text,
        )
    }

    fun deleteWish(id: Long) {
        dbQuery.removeWish(id)
    }
}
