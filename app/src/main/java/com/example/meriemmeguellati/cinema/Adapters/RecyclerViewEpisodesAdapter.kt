package com.example.meriemmeguellati.cinema.Adapters

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meriemmeguellati.cinema.Model.Episode
import com.example.meriemmeguellati.cinema.Model.Film

import com.example.meriemmeguellati.cinema.Model.SectionDataModel
import com.example.meriemmeguellati.cinema.R


import java.util.ArrayList

class RecyclerViewEpisodesAdapter(private val mContext: Context, private val dataList: ArrayList<Episode>?) : RecyclerView.Adapter<RecyclerViewEpisodesAdapter.ItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {


        val singleSectionItems = dataList

        itemRowHolder.itemTitle.text = "Episodes"

        val itemListDataAdapter = SectionListEpisodesAdapter(mContext, singleSectionItems)

        itemRowHolder.recycler_view_list.setHasFixedSize(true)
        itemRowHolder.recycler_view_list.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        itemRowHolder.recycler_view_list.adapter = itemListDataAdapter


    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTitle: TextView

        var recycler_view_list: RecyclerView


        init {

            this.itemTitle = view.findViewById<TextView>(R.id.itemTitle)
            this.recycler_view_list = view.findViewById<RecyclerView>(R.id.recycler_view_list)


        }

    }

}