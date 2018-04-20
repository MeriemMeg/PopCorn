package com.example.meriemmeguellati.cinema.Adapters


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.Comment

import com.example.meriemmeguellati.cinema.R


class CommentsFragment : Fragment() {
    var commentaires = ArrayList<Comment>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var mview : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mview =  inflater.inflate(R.layout.fragment_comments, container, false)
        val n = this.arguments.getInt("size") -1
        for (i in 0..n){
            this.commentaires.add(this.arguments.getSerializable("commentaire"+i.toString()) as Comment)
        }

        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.comments_recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 1)

       // val comments = prepareComments()

        val adapter = CommentsAdapter(this.context, this.commentaires)

        recyclerView.adapter = adapter

    }
    private fun prepareComments() : ArrayList<Comment>{


        val data = Data(resources)
        data.createComments()

        return data.commentaire
    }
}
