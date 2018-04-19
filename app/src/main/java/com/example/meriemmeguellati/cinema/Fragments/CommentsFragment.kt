package com.example.meriemmeguellati.cinema.Adapters


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.meriemmeguellati.cinema.R


class CommentsFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var mview : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mview =  inflater.inflate(R.layout.fragment_comments, container, false)
        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.comments_recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 1)

        val comments = prepareComments()

        val adapter = CommentsAdapter(this.context, comments)

        recyclerView.adapter = adapter

    }
    private fun prepareComments() : ArrayList<Comment>{
        var profile: IntArray = intArrayOf(
                R.drawable.jamescorden,
                R.drawable.jenniferlawrence,
                R.drawable.margotrobbie,
                R.drawable.profile,
                R.drawable.rosebyrne,
                R.drawable.samneill
        )

        val res = resources
        val comments = ArrayList<Comment>()
        comments.add(Comment(
                        res.getStringArray(R.array.comment_1)[0].toInt(),
                        res.getStringArray(R.array.comment_1)[1],
                        res.getStringArray(R.array.comment_1)[2],
                        profile[0]))
        comments.add(Comment(
                res.getStringArray(R.array.comment_2)[0].toInt(),
                res.getStringArray(R.array.comment_2)[1],
                res.getStringArray(R.array.comment_2)[2],
                profile[1]))
        comments.add(Comment(
                res.getStringArray(R.array.comment_3)[0].toInt(),
                res.getStringArray(R.array.comment_3)[1],
                res.getStringArray(R.array.comment_3)[2],
                profile[2]))
        comments.add(Comment(
                res.getStringArray(R.array.comment_4)[0].toInt(),
                res.getStringArray(R.array.comment_4)[1],
                res.getStringArray(R.array.comment_4)[2],
                profile[3]))
        comments.add(Comment(
                res.getStringArray(R.array.comment_5)[0].toInt(),
                res.getStringArray(R.array.comment_5)[1],
                res.getStringArray(R.array.comment_5)[2],
                profile[4]))
        comments.add(Comment(
                res.getStringArray(R.array.comment_6)[0].toInt(),
                res.getStringArray(R.array.comment_6)[1],
                res.getStringArray(R.array.comment_6)[2],
                profile[5]))

        return comments
    }
}
