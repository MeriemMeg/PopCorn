package com.example.meriemmeguellati.cinema

import com.example.meriemmeguellati.cinema.APIresponses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIpersons {

    // détails d'une personne
    @GET("person/{person_id}")
    fun getDetailPerson(@Path("person_id") person_id: Int, @Query("language") language: String): Call<SerieDetailsResponse>

    // les films dans lesquels une personne est apparue
    @GET("person/{person_id}/movie_credits")
    fun getMoviesPerson(@Path("person_id") person_id: Int, @Query("language") language: String): Call<MovieResponse>

}