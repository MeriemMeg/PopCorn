package com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls

import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.TMDBapi.APIendpoints.APIpersons
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIpersonnesCall {

    private var apiHub: APIpersons

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

        apiHub = retrofit.create<APIpersons>(APIpersons::class.java!!)
    }

    fun getService(): APIpersons {
        return apiHub
    }
}