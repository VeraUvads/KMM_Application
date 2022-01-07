package com.example.kmmapplication.network

import com.example.kmmapplication.data.Wish
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.json.serializer.*
import io.ktor.client.request.*

class WishApi {
    // TODO: 07.01.2022 try add back-end
    val httpClient = HttpClient {
        engine {
            // why couldn't add another custom properties
//            threadsCount = 8
//            pipelining = true
//            proxy = ProxyBuilder.http("http://proxy-server.com/")
        }

    }

    suspend fun getAllWished(): List<Wish> {
//        return httpClient.get(WISHES_ENDPOINT)
        return emptyList()
    }

    companion object {
        private const val WISHES_ENDPOINT = "NotYetImplemented"
    }
}
