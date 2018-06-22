package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FichePersonnesActivity
import com.example.meriemmeguellati.cinema.Model.Chaine
import com.example.meriemmeguellati.cinema.Model.Personne

import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class SectionListChainesAdapter(private val mContext: Context, private val itemsList: ArrayList<Chaine>?) : RecyclerView.Adapter<SectionListChainesAdapter.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.chaine_card, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val singleItem = itemsList!![i]

        holder.date.text = singleItem.date
        holder.time.text = singleItem.heure
//        holder.image.setImageResource(singleItem.image)



    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var date: TextView
        var time: TextView
        var image : ImageView

        // protected var itemImage: ImageView


        init {

            this.date = view.findViewById<TextView>(R.id.date)
            this.time = view.findViewById<TextView>(R.id.time)
            this.image = view.findViewById<ImageView>(R.id.chaineImage)


        }

    }

}