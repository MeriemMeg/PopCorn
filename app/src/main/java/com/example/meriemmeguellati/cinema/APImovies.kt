package com.example.meriemmeguellati.cinema

import com.example.meriemmeguellati.cinema.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APImovies {

    //films en cours de projection
    @GET("movie/now_playing")
     fun getNowPlayingMovie(@Query("language") language: String): Call<NowPlayingResponse>

    // détails d'un film
    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id: String, @Query("language") language: String): Call<MovieDetailResponse>

    //films similaires
    @GET("movie/{movie_id}/similar")
    fun getSimilarmovies(@Path("movie_id") movie_id: String, @Query("language") language: String) : Call<NowPlayingResponse>

    //personnes associés à un film
    @GET("movie/{movie_id}/credits")
    fun getAssociatedPersons(@Path("movie_id") movie_id: String) : Call<CreditsResponse>

    //commentaires d'un film
    @GET("movie/{movie_id}/reviews")
    fun getComments(@Path("movie_id") movie_id: String) : Call<ReviewsResponse>
}