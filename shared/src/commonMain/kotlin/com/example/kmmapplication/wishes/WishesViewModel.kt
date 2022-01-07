package com.example.kmmapplication.wishes

import com.example.kmmapplication.WishSDK
import com.example.kmmapplication.data.Wish
import com.example.kmmapplication.utils.DatabaseDriverFactory
import com.example.kmmapplication.utils.SharedViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class WishesViewModel() : SharedViewModel() {

    private var wishesStore: WishStore? = null

    private val _wishes by lazy { wishesStore?.wishes }
    val wishes: Flow<List<Wish>> by lazy { _wishes ?: flowOf(emptyList()) }

    // find another way
    fun getWishes(databaseDriverFactory: DatabaseDriverFactory) {
        if (wishesStore == null) {
            wishesStore = WishStoreImpl(WishSDK(databaseDriverFactory))
        }
        sharedScope.launch {
            wishesStore?.update()
        }
    }

    fun removeWish(id: Long) {
        sharedScope.launch {
            wishesStore?.removeWish(id)
        }
    }
}
