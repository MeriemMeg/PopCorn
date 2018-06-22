package com.example.meriemmeguellati.cinema.TMDBapi.APIendpoints

import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIseries {

    //Séries en cours de projection
    @GET("tv/on_the_air")
    fun getNowPlayingSeries(@Query("language") language: String): Call<LatestSeriesResponse>

    //Séries populaires
    @GET("tv/popular")
    fun getPopularSeries(@Query("language") language: String): Call<LatestSeriesResponse>

    // détails d'une série
    @GET("tv/{tv_id}")
    fun getDetailSerie(@Path("tv_id") tv_id: Int, @Query("language") language: String): Call<SerieDetailsResponse>

    //series similaires
    @GET("tv/{tv_id}/similar")
    fun getSimilarSeries(@Path("tv_id") tv_id: Int, @Query("language") language: String) : Call<LatestSeriesResponse>

    //personnes associés à une série
    @GET("tv/{tv_id}/credits")
    fun getAssociatedPersons(@Path("tv_id") tv_id: Int) : Call<CreditsResponse>

    //commentaires d'une série
    @GET("tv/{tv_id}/reviews")
    fun getComments(@Path("tv_id") tv_id: Int) : Call<ReviewsResponse>

    //détails d'une saison
    @GET("tv/{tv_id}/season/{season_number}")
    fun getSeasonDetails(@Path("tv_id") tv_id: Int,@Path("season_number")season_number:Int) : Call<SeasonDetailsResponse>
}