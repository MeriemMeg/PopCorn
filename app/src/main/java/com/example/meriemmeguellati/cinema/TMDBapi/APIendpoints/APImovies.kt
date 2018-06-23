package com.example.meriemmeguellati.cinema.TMDBapi.APIendpoints

import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APImovies {

    //films en cours de projection
    @GET("movie/now_playing")
     fun getNowPlayingMovie(@Query("language") language: String): Call<NowPlayingResponse>

    //films en cours de projection
    @GET("movie/popular")
    fun getPopularMovie(@Query("language") language: String): Call<NowPlayingResponse>

    //films en cours de projection
    @GET("movie/latest")
    fun getLatestMovie(@Query("language") language: String): Call<MovieResponse>

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

    //videos d'un film
    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movie_id: Int) : Call<VideoResponse>
}