package com.example.rtkl

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val Base_Url = "https://newsapi.org/"
const val API_KEY = "96a7d9b5237f4329b9df2e476afaa50d"

interface NewsInterface{

        @GET("v2/top-headlines?apiKey=$API_KEY")
        fun getHeadlines(@Query("country")country: String,@Query("page") page: Int) : Call<News>
}

object NewsService{
        val newsInstance: NewsInterface
        init{
                val retrofit= Retrofit.Builder()
                        .baseUrl(Base_Url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
        newsInstance= retrofit.create(NewsInterface::class.java)
        }
}