package com.example.homework

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
//import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
//import com.bumptech.glide.integration.compose.GlideImage
import com.example.homework.ui.theme.HomeWorkTheme

class MainActivity : ComponentActivity() {
    //@OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        App.init(applicationContext)

        val viewModel: MyViewModel by viewModels {
            MyViewModel.provideFactory(
                // App.cityRepository,
                //App.weatherRepository,
                App.booksRepository,
                App.unsplashApi,
                this
            )
        }
        //KKKLLLLA
        viewModel.getBooks("KKKLLLLA")
        viewModel.getSplash("bunny")


        setContent {
            HomeWorkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val s =viewModel.books.collectAsState().value
                    val ss =viewModel.list.collectAsState().value
                    LazyVerticalGrid(columns = GridCells.Fixed(2)){

                        items(ss.size){
                            BookItem(link = ss[it].imageUrl)
                        }
                    }

                   // Greeting("Android")
                    //AsyncImage (model = "https://buffer.com/library/content/images/size/w1200/2023/09/instagram-image-size.jpg" ,contentDescription = null , modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItem(link:String){
    Box(modifier =Modifier.background(Color.Black).width(200.dp).height(200.dp)
             ){
        Log.d("12132321132" ,link)
        AsyncImage(model = link, contentDescription = null, contentScale = ContentScale.FillBounds)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

