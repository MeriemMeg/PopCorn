package com.example.meriemmeguellati.cinema.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.example.meriemmeguellati.cinema.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SalleMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salle_map)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        var long :Double = 0.0
        var lat: Double = 0.0
        val res = resources
        if (message.equals("La salle de cinéma Atlas")){
            long = res.getStringArray(R.array.salle_1)[4].toDouble()
            lat = res.getStringArray(R.array.salle_1)[5].toDouble()
        } else if (message.equals("La salle de cinéma cosmos")){
            long = res.getStringArray(R.array.salle_2)[4].toDouble()
            lat = res.getStringArray(R.array.salle_2)[5].toDouble()
        } else if (message.equals("La salle de cinéma inb khaldoun")){
            long = res.getStringArray(R.array.salle_3)[4].toDouble()
            lat = res.getStringArray(R.array.salle_3)[5].toDouble()
        }

        val salle = LatLng(long, lat)
        googleMap!!.clear()
        googleMap!!.addMarker(MarkerOptions().position(salle)
                .title(message))
        googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(salle))
    }
}
