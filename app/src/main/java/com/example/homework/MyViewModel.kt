package com.example.homework

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.homework.api.UnsplashApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel(val repository: BooksRepository ,val unsplashApi: UnsplashApi):ViewModel() {
    var loading =MutableStateFlow<Boolean>(true)
    var books =MutableStateFlow<List<Book>>(mutableListOf())
    var list =MutableStateFlow<List<Book>>(mutableListOf())

    fun getBooks(text:String){
        viewModelScope.launch {
            val res =repository.getBooks(text)

            when (res) {
                is Resource.Success -> {
                    Log.d("12132321132" , res.data?.get(0)!!.imageUrl.toString())
                    books.value =res.data!!

                }

                is Resource.Loading -> {
                    Log.d("12132321132","Loading")

                }

                is Resource.Error -> {
                    Log.d("12132321132","Errorrrr")
                }
            }
        }


    }

    fun getSplash(text:String) {
        viewModelScope.launch {




            if (text.isNotBlank()) {
                Log.d(
                    "adsdwwwwwww",
                    "amongus"
                )

                try{
                    val s = unsplashApi.searchPhotos(
                        "search/photos?page=1&query=${text.toString()}}",
                        2,
                        20
                    )
                    var l = mutableListOf<Book>()
                    for (a in 0 until 20) {
                        l.add(
                            Book(s.results[a].urls.regular)

                        )

                    }
                    Log.d(
                        "adsdwwwwwww",
                        "amongus"
                    )
                    list.value = l
                }catch (e:Exception){
                    Log.d(
                        "adsdwwwwwww",
                        "$e"
                    )
                }

            }else{
                Log.d(
                    "adsdwwwwwww",
                    "cheeee?"
                )
            }
        }
    }
    companion object{
        fun provideFactory(

            booksRepository: BooksRepository,
            unsplashApi: UnsplashApi,

            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return MyViewModel(
                        booksRepository,
                        unsplashApi
                        ) as T
                }
            }
    }
}