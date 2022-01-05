package com.example.kmmapplication.wishes

import kotlinx.coroutines.Dispatchers

object WishViewerServiceLocator {

    val wishStore by lazy {
        WishStoreImpl()
    }

    val defaultDispatcher = Dispatchers.Default
}
