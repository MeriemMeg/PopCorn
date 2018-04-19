package com.example.meriemmeguellati.cinema.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.Adapters.SallesAdapter
import com.example.meriemmeguellati.cinema.Model.Salle
import com.example.meriemmeguellati.cinema.R

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class SallesFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var mview: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mview = inflater.inflate(R.layout.fragment_salles, container, false)
        return mview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = mview.findViewById(R.id.recyler_view)

        recyclerView.layoutManager = GridLayoutManager(this.context, 1)

        val salles = prepareCovers()

        val adapter = SallesAdapter(this.context, salles)

        recyclerView.adapter = adapter
    }
    private fun prepareCovers() : ArrayList<Salle>{
        var images: IntArray = intArrayOf(
                R.drawable.cinemaatlas,
                R.drawable.cinemacosmos,
                R.drawable.cinemaibnkhaldoun
        )

        val res = resources
        val salles = ArrayList<Salle>()

        salles.add(Salle(
                res.getStringArray(R.array.salle_1)[0],
                res.getStringArray(R.array.salle_1)[1],
                res.getStringArray(R.array.salle_1)[2],
                images[0]))
        salles.add(Salle(
                res.getStringArray(R.array.salle_2)[0],
                res.getStringArray(R.array.salle_2)[1],
                res.getStringArray(R.array.salle_2)[2],
                images[1]))
        salles.add(Salle(
                res.getStringArray(R.array.salle_3)[0],
                res.getStringArray(R.array.salle_3)[1],
                res.getStringArray(R.array.salle_3)[2],
                images[2]))

        return salles
    }

}
