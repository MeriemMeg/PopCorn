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
import com.example.meriemmeguellati.cinema.Model.Personne

import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class SectionListPersonnesAdapter(private val mContext: Context, private val itemsList: ArrayList<Personne>?) : RecyclerView.Adapter<SectionListPersonnesAdapter.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_single_card, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val singleItem = itemsList!![i]

        holder.tvTitle.text = singleItem.nom

        holder.image.setBackgroundResource(singleItem.fiche)
        holder.image.setOnClickListener {
            val intent = Intent(mContext, FichePersonnesActivity::class.java)
            intent.putExtra("Personne", singleItem)
            mContext.startActivity(intent)
        }


        /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView
        var image : FrameLayout

        // protected var itemImage: ImageView


        init {

            this.tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            this.image = view.findViewById<FrameLayout>(R.id.imageFilmLie)

            view.setOnClickListener { v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show() }


        }

    }

}