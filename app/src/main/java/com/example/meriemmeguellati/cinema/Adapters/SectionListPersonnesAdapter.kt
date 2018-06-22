package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
import android.app.Activity
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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.FichePersonnesActivity
import com.example.meriemmeguellati.cinema.BuildConfig
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

        //holder.image.setBackgroundResource(singleItem.fiche)
        if(singleItem.gender == 1){
            Glide.with(mContext)
                    .load(BuildConfig.BASE_URL_IMG + "w154" + singleItem.profil)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.femmeholder)
                            .centerCrop()
                    )
                    .into(holder.itemImage)
        } else {
            Glide.with(mContext)
                    .load(BuildConfig.BASE_URL_IMG + "w154" + singleItem.profil)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.hommeholder)
                            .centerCrop()
                    )
                    .into(holder.itemImage)
        }

        holder.image.setOnClickListener {
            val intent = Intent(mContext, FichePersonnesActivity::class.java)
            intent.putExtra("Personne", singleItem)
            mContext.startActivity(intent)
            val activity = mContext as Activity
            activity.finish()

        }




    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView
        var image : FrameLayout
        var itemImage: ImageView



        init {

            this.tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            this.image = view.findViewById<FrameLayout>(R.id.imageFilmLie)
            this.itemImage = view.findViewById<ImageView>(R.id.posterr)

            view.setOnClickListener { v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show() }


        }

    }

}