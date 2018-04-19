package com.example.meriemmeguellati.cinema.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.R
/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class FilmsAdapter(val context: Context, val filmsList: ArrayList<Film>): RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film: Film = filmsList[position]
        holder.titre.text = film.titre
        holder.thumbnail.setImageResource(film.affiche)
        holder.thumbnail.setOnClickListener {
            val intent = Intent(mContext, FicheFilmActivity::class.java)
            intent.putExtra("film", film)
            mContext.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titre = itemView.findViewById<TextView>(R.id.titre)
        var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
    }
}