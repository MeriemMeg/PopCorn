package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.Film

import com.example.meriemmeguellati.cinema.Model.SectionDataModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class RecyclerViewResultsAdapter(private val mContext: Context, private val dataList: ArrayList<Film>?) : RecyclerView.Adapter<RecyclerViewResultsAdapter.ItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.seach_item, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {


        val singleSectionItems = dataList!![i]

        itemRowHolder.itemTitle.text = singleSectionItems.titre
        Glide.with(mContext)
                .load(BuildConfig.BASE_URL_IMG + "w154" + singleSectionItems.posterPath)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultposter)
                        .centerCrop()
                )
                .into(itemRowHolder.poster)
        itemRowHolder.item.setOnClickListener {
            val intent = Intent(mContext, FicheFilmActivity::class.java)
            intent.putExtra("film", singleSectionItems)
            mContext.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTitle: TextView

        var poster: ImageView
        var item : FrameLayout


        init {

            this.itemTitle = view.findViewById<TextView>(R.id.title)
            this.poster = view.findViewById<ImageView>(R.id.poster)
            this.item  = view.findViewById<FrameLayout>(R.id.frame)




        }


    }

}