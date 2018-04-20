package com.example.meriemmeguellati.cinema.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.meriemmeguellati.cinema.R
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.RatingBar
import com.example.meriemmeguellati.cinema.Model.Comment
import de.hdodenhof.circleimageview.CircleImageView


class CommentsAdapter(val context: Context, val commentsList: ArrayList<Comment>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.comment_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = commentsList[position]
        holder.message.text = comment.message
        holder.arrow.setImageResource(R.drawable.arrow)
        holder.nom.text = comment.nom
        holder.rating.rating = comment.rating.toFloat()
        holder.profil.setImageResource(comment.profile)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val message = itemView.findViewById<TextView>(R.id.message)
        val arrow = itemView.findViewById<ImageView>(R.id.arrow)
        val nom = itemView.findViewById<TextView>(R.id.nom)
        val rating = itemView.findViewById<RatingBar>(R.id.rating)
        val profil = itemView.findViewById<CircleImageView>(R.id.profile)
    }

    fun decodeToBitmap(decodedByte: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}