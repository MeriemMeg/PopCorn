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
import com.example.meriemmeguellati.cinema.Activities.FicheSaisonActivity
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.Saison

import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class SectionListSaisonAdapter(private val mContext: Context, private val itemsList: ArrayList<Saison>?) : RecyclerView.Adapter<SectionListSaisonAdapter.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_single_card, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val singleItem = itemsList!![i]

        holder.tvTitle.text = "saison "+singleItem.num.toString()
        Glide.with(mContext)
                .load(BuildConfig.BASE_URL_IMG + "w154" + singleItem.poster_path)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultposter)
                        .centerCrop()
                )
                .into(holder.image)
        holder.itemImage.setOnClickListener {
            val intent = Intent(mContext, FicheSaisonActivity::class.java)
            intent.putExtra("saison", singleItem)
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
        var itemImage: FrameLayout
        var image: ImageView


        init {

            this.tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            this.itemImage = view.findViewById<FrameLayout>(R.id.imageFilmLie)
            this.image = view.findViewById<ImageView>(R.id.posterr)


            view.setOnClickListener { v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show() }


        }

    }

}