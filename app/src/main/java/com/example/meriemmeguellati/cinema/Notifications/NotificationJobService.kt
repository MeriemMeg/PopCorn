package com.example.meriemmeguellati.cinema.Notifications

import android.annotation.TargetApi
import android.app.job.JobParameters
import android.content.Intent
import android.app.job.JobService
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.Language
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.MovieResponse
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationJobService : JobService() {



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartJob(params: JobParameters): Boolean {

        checkForNewMovie(applicationContext)
        val service = Intent(applicationContext, NotificationJobService::class.java)
        applicationContext.startService(service)
        SchedulerJob.scheduleJob(applicationContext) // reschedule the job*/


        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }

    fun checkForNewMovie (context: Context){

         var apiMoviesCall: Call<MovieResponse>? = null
         val apiMovies = APImoviesCall()
            apiMoviesCall = apiMovies.getService().getLatestMovie(Language().Country())
            apiMoviesCall!!.enqueue(object : Callback<MovieResponse> {
                @TargetApi(Build.VERSION_CODES.M)
                @RequiresApi(Build.VERSION_CODES.M)
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {

                        val item = response.body()
                        var movie : Film

                        if (item != null) {
                            movie = Film(item.title!!,"", item.overview!!,item.posterPath?:"", R.drawable.defaultposter)
                            movie.backdrop_path = item.backdropPath?:""
                            movie.id = item.id
                            //Toast.makeText(applicationContext,item.posterPath, Toast.LENGTH_SHORT).show()
                            if(verifyPreferences("notifications")){
                                var send = false
                                if(item.genres!!.isEmpty()){
                                    send = true
                                } else{
                                    for (genre in item.genres!!){
                                        if (verifyPreferences(genre?.name?:"Drama")) send = true
                                        //Toast.makeText(applicationContext,genre.name, Toast.LENGTH_SHORT).show()


                                    }

                                }
                                if(send){
                                    if(!verifyPrecedentMovie(movie.id)){
                                        val n = NotificationCreator(context,movie)
                                        n.CreateNotifClick()
                                        System.out.print("notiiiif")
                                        saveNewMovie(movie.id)
                                    }


                                }

                            }


                        }

                    }


                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    //loadFailed()
                }
            })



    }

    fun verifyPreferences (genre : String) : Boolean{
        val PARAMS_NAME = "PARAMS"
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val setting = params.getInt(genre, 0)
        if(setting == 1) return true
        else return false
    }

    fun verifyPrecedentMovie (id :Int) : Boolean{
        val PARAMS_NAME = "PARAMS"
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val setting = params.getInt("precedent", 123)
        if(setting == id) return false
        else return false
    }

    fun saveNewMovie (id :Int) {
        val PARAMS_NAME = "PARAMS"
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val editeur = params.edit()
        editeur.putInt("precedent", id)
        editeur.commit()
    }

    companion object {
        private val TAG = "SyncService"
    }

}