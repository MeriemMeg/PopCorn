package com.example.meriemmeguellati.cinema.TMDBapi.APIendpoints

import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIsearch {

    //rechercher un film
    @GET("search/movie")
    fun searchMovie(@Query("language") language: String,@Query("include_adult") adult: Boolean): Call<NowPlayingResponse>

    //rechercher une serie
    @GET("search/tv")
    fun searchSerie(@Query("language") language: String): Call<LatestSeriesResponse>

}