package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewFilmLiesAdapter
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewSaisonAdapter
import com.example.meriemmeguellati.cinema.R
import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewPersonnesAdapter
import com.example.meriemmeguellati.cinema.Model.*


class FicheEpisodeActivity : AppCompatActivity() {

    lateinit var episode : Episode
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_personne_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar()!!.title=""



        val intent = intent
         this.episode = intent.getSerializableExtra("Episode") as Episode

        val nomActeur = findViewById<TextView>(R.id.nomActeur)
        nomActeur.text = this.episode.serie
        val naissance = findViewById<TextView>(R.id.bearthday)
        naissance.text = "Episode "+this.episode.saison.toString() + " x "+ this.episode.num.toString()



        val background = findViewById<FrameLayout>(R.id.film_background)
        background.setBackgroundResource(this.episode.poster)
        var videoView = findViewById<VideoView>(R.id.videoView)


        // Set the media controller buttons

        val mediaController = MediaController(this)

        // Set the videoView that acts as the anchor for the MediaController.

        mediaController?.setAnchorView(videoView)

        // Set MediaController for VideoView
        //  videoView.setMediaController(mediaController)





        val description = findViewById<TextView>(R.id.film_description)
        description.text = "This is the episode n°"+this.episode.num.toString()+" of the seaison "+this.episode.saison.toString()+" of" + this.episode.serie +"."


        val icon = findViewById<ImageButton>(R.id.play_stop)
        icon.setImageResource(R.drawable.ic_ondemand_video_white_24dp)


        /*val film_liées_recycler_view = findViewById<RecyclerView>(R.id.filmgraphie)

        film_liées_recycler_view.setHasFixedSize(true)
        //
        val adapter2 = RecyclerViewFilmLiesAdapter(this, personne.filmographie)

        film_liées_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        film_liées_recycler_view.adapter = adapter2*/

    }

    // Find ID corresponding to the name of the resource (in the directory raw).
    fun getRawResIdByName(resName: String): Int {
        val pkgName = this.packageName
        // Return 0 if not found.
        val resID = this.resources.getIdentifier(resName, "raw", pkgName)
        Log.i("AndroidVideoView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_follow -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_rate -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        R.id.action_follow -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }
        android.R.id.home->{
            this.finish()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


}
