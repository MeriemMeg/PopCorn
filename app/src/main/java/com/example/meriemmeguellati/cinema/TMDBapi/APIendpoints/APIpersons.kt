package com.example.meriemmeguellati.cinema.TMDBapi.APIendpoints

import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIpersons {

    // détails d'une personne
    @GET("person/{person_id}")
    fun getDetailPerson(@Path("person_id") person_id: Int, @Query("language") language: String): Call<PersonDetailsResponse>

    // les films dans lesquels une personne est apparue
    @GET("person/{person_id}/movie_credits")
    fun getMoviesPerson(@Path("person_id") person_id: Int, @Query("language") language: String): Call<PersonneCastResponse>

    //criiques d'une personne
    @GET("movie/{movie_id}/reviews")
    fun getComments(@Path("movie_id") movie_id: String) : Call<ReviewsResponse>

}