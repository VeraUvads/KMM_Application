package com.example.kmmapplication.wishes

import com.example.kmmapplication.utils.SharedViewModel

class WishesViewModel(
    wishesStore: WishStore = WishViewerServiceLocator.wishStore,
) : SharedViewModel() {

    private val _wishes = wishesStore.wishes
}
