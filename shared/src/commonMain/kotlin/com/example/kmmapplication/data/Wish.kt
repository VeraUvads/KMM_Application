package com.example.kmmapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wish(
    @SerialName("id") val id: Long,
    @SerialName("text") val text: String
)
