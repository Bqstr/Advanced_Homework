package com.example.homework

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepositoryImpl(val api: BookApi):BooksRepository {
    override suspend  fun getBooks(text:String): Resource<List<Book>> {



        val response = try {

            val ss =api.getBooksByQuery(text)
            val resp = mutableListOf<Book>()

            for(a in 0 until ss.items.size){
                val s  =Book( ss.
                items.get(a).volumeInfo.imageLinks.thumbnail)
                resp.add(s)
            }
            Log.d("12132321132","what")
            return Resource.Success(resp)

        } catch(e: Exception) {
            Log.d("12132321132" ,e.toString())
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Loading()










//
//
//        call.enqueue(object : Callback<BookResponse> {
//            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
//                if (response.isSuccessful) {
//                    val bookResponse = response.body()
//                    response
//                    for(a in 0 until bookResponse!!.items.size){
//                        listOfBook.add(Book(
//                            bookResponse.items.get(a).volumeInfo.imageLinks.thumbnail
//                        )
//
//                        )
//                    }
//                } else {
//                    Log.d("LOGGING", "ERROR")
//                    // Handle error
//                }
//            }
//
//            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
//                Log.d("LOGGING", "Failiare")
//            }
//        })
//        return listOfBook
    }

    }

