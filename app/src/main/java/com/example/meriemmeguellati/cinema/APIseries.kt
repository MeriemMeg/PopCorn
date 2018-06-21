package com.example.meriemmeguellati.cinema

import com.example.meriemmeguellati.cinema.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIseries {

    //Séries en cours de projection
    @GET("tv/on_the_air")
    fun getNowPlayingSeries(@Query("language") language: String): Call<LatestSeriesResponse>

    // détails d'une série
    @GET("tv/{tv_id}")
    fun getDetailMovie(@Path("tv_id") tv_id: Int, @Query("language") language: String): Call<SerieDetailsResponse>

    //series similaires
    @GET("tv/{tv_id}/similar")
    fun getSimilarmovies(@Path("tv_id") tv_id: Int, @Query("language") language: String) : Call<LatestSeriesResponse>

    //personnes associés à un film
    @GET("tv/{tv_id}/credits")
    fun getAssociatedPersons(@Path("movie_id") movie_id: String) : Call<CreditsResponse>

    //commentaires d'un film
    @GET("tv/{tv_id}/reviews")
    fun getComments(@Path("movie_id") movie_id: String) : Call<ReviewsResponse>
}