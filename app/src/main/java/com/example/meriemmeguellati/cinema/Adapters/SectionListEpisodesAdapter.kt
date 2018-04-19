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
import android.widget.TextView
import android.widget.Toast
import com.example.meriemmeguellati.cinema.Activities.FicheEpisodeActivity
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Model.Episode
import com.example.meriemmeguellati.cinema.Model.Film
import com.example.meriemmeguellati.cinema.Model.Saison

import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class SectionListEpisodesAdapter(private val mContext: Context, private val itemsList: ArrayList<Episode>?) : RecyclerView.Adapter<SectionListEpisodesAdapter.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_single_card, null)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {

        val singleItem = itemsList!![i]

        holder.tvTitle.text = " S" + singleItem.saison.toString()+" E"+singleItem.num.toString()
        holder.image.setBackgroundResource(singleItem.affiche)
        holder.image.setOnClickListener {
            val intent = Intent(mContext, FicheEpisodeActivity::class.java)
            intent.putExtra("Episode", singleItem)
            mContext.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle: TextView
        var image : FrameLayout


        init {

            this.tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            this.image = view.findViewById<FrameLayout>(R.id.imageFilmLie)
            // this.itemImage = view.findViewById<ImageView>(R.id.itemImage)


            view.setOnClickListener {
                v -> Toast.makeText(v.context, tvTitle.text, Toast.LENGTH_SHORT).show()


            }


        }

    }

}