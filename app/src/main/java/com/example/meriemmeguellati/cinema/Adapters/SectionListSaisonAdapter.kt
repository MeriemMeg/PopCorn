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
import com.example.meriemmeguellati.cinema.Activities.FicheSaisonActivity
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
        holder.itemImage.setBackgroundResource(singleItem.affiche)
        holder.itemImage.setOnClickListener {
            val intent = Intent(mContext, FicheSaisonActivity::class.java)
            intent.putExtra("saison", singleItem)
            mContext.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView
        var itemImage: FrameLayout


        init {

            this.tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            this.itemImage = view.findViewById<FrameLayout>(R.id.imageFilmLie)


            view.setOnClickListener { v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show() }


        }

    }

}