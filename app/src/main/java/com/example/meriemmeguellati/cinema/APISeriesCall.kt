package com.example.meriemmeguellati.cinema

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

 class APISeriesCall {
     private var apiHub: APIseries

     init {
         val client = OkHttpClient.Builder()
                 .addInterceptor { chain ->
                     var original = chain.request()
                     val httpUrl = original.url()
                             .newBuilder()
                             .addQueryParameter("api_key", BuildConfig.API_KEY)
                             .build()

                     original = original.newBuilder()
                             .url(httpUrl)
                             .build()

                     chain.proceed(original)
                 }
                 .build()

         val retrofit = Retrofit.Builder()
                 .client(client)
                 .baseUrl(BuildConfig.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()

         apiHub = retrofit.create<APIseries>(APIseries::class.java!!)
     }

     fun getService(): APIseries {
         return apiHub
     }
 }