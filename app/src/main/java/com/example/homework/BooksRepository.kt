package com.example.homework

interface BooksRepository {
    suspend fun getBooks(text:String):Resource<List<Book>>
}