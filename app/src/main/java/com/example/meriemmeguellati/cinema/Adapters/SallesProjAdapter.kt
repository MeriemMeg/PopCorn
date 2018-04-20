package com.example.meriemmeguellati.cinema.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.meriemmeguellati.cinema.Model.Salle
import com.example.meriemmeguellati.cinema.R


class SallesProjAdapter(val context: Context, val sallesList: ArrayList<Salle>): RecyclerView.Adapter<SallesProjAdapter.ViewHolder>() {

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.proj_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return sallesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val salle: Salle = sallesList[position]

        holder.nom.text = salle.nom
        holder.image.setImageResource(salle.thumbnail)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nom = itemView.findViewById<TextView>(R.id.nom)
        val image = itemView.findViewById<ImageView>(R.id.salle_img)
    }
}