package com.example.kmmapplication.utils

import kotlinx.coroutines.CoroutineScope

expect open class SharedViewModel() {
    protected val sharedScope: CoroutineScope

    open fun onCleared()
}
