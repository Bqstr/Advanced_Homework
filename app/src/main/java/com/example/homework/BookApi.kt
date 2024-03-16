package com.example.homework

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface BookApi {
companion object {
     const val BASE_URL = "https://www.googleapis.com/books/v1/"
}
        @GET("volumes")
        suspend fun getBooksByQuery(@Query("q") query: String): BookResponse

}