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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FicheSerieActivity
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Serie
import com.example.meriemmeguellati.cinema.R
/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class SeriesAdapter(val context: Context, val seriesList: ArrayList<Serie>): RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serie: Serie = seriesList[position]
        holder.titre.text = serie.titre
        Glide.with(mContext)
                .load(BuildConfig.BASE_URL_IMG + "w154" + serie.posterPath)
                .apply(RequestOptions()
                        .placeholder(R.drawable.img2)
                        .centerCrop()
                )
                .into(holder.thumbnail)
        holder.thumbnail.setOnClickListener {
            val intent = Intent(mContext, FicheSerieActivity::class.java)
            intent.putExtra("serie", serie)
            mContext.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titre = itemView.findViewById<TextView>(R.id.titre)
        var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
    }
}