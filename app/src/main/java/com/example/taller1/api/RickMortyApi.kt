package com.example.taller1.api

import com.example.taller1.data.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickMortyApi(
    private val client: HttpClient = defaultHttpClient()
) {
    suspend fun getCharacters(page: Int): ApiResponse {
        return client.get("character/") {
            parameter("page", page)
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun defaultHttpClient(): HttpClient {
            return HttpClient(OkHttp) {
                expectSuccess = true

                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                        }
                    )
                }

                defaultRequest {
                    url(BASE_URL)
                }
            }
        }
    }
}
