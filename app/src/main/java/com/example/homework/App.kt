package com.example.homework

import android.content.Context
import com.example.homework.api.UnsplashApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object App {
    lateinit var applicationContext: Context
    fun init(context:Context){
        applicationContext =context
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(BookApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retroF =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val unsplashApi = retroF.create(UnsplashApi::class.java)


    private val bookApi = retrofit.create(BookApi::class.java)


    val booksRepository:BooksRepository by lazy{
        BooksRepositoryImpl(bookApi )
    }




}