package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewFilmLiesAdapter
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewSaisonAdapter
import com.example.meriemmeguellati.cinema.Model.SectionDataModel
import com.example.meriemmeguellati.cinema.Model.SingleItemModel
import com.example.meriemmeguellati.cinema.R
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.meriemmeguellati.cinema.Model.Film
import android.util.Log
import android.view.*
import android.widget.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewPersonnesAdapter


class FicheFilmActivity : AppCompatActivity() {


    lateinit var film : Film
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_film_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar()!!.title=""

        val intent = intent
         this.film = intent.getSerializableExtra("film") as Film

        val titre = findViewById<TextView>(R.id.film_name)
        titre.text = film.titre

        val playStop = findViewById<ImageButton>(R.id.play_stop)
        playStop.setImageResource(R.drawable.ic_play_arrow_white_24dp)

        val background = findViewById<FrameLayout>(R.id.film_background)
        background.setBackgroundResource(film.affiche)
        var videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

        try {
            // ID of video file.
            val id = this.getRawResIdByName(film.trailer)
            videoView.setVideoURI(Uri.parse("android.resource://$packageName/$id"))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }
        videoView.setBackgroundResource(film.trailerposter)

        videoView.requestFocus()

        playStop.setOnClickListener {
            videoView.start()
            getSupportActionBar()!!.hide()
            playStop.setVisibility(View.INVISIBLE);
            videoView.setBackgroundResource(0)

        }

        videoView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                videoView.pause()
                getSupportActionBar()!!.show()
                playStop.setVisibility(View.VISIBLE);

                return false
            }
        })

        val description = findViewById<TextView>(R.id.film_description)
        description.text = film.description

        val showComments = findViewById<TextView>(R.id.nb_comments)
        showComments.text = "Commentaires (4)"
        showComments.setOnClickListener {
            showFragment(CommentsFragment())
        }


        val my_recycler_view = findViewById<RecyclerView>(R.id.personnes_associees)

        my_recycler_view.setHasFixedSize(true)


        val adapter = RecyclerViewPersonnesAdapter(this, film.personnesAssociés)

        my_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        my_recycler_view.adapter = adapter


        val film_liées_recycler_view = findViewById<RecyclerView>(R.id.film_lies)

        film_liées_recycler_view.setHasFixedSize(true)
  //
        val adapter2 = RecyclerViewFilmLiesAdapter(this, film.filmsLiés)

        film_liées_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        film_liées_recycler_view.adapter = adapter2


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
            this.film.suivre();
            true
        }

        R.id.action_rate -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        R.id.action_comment -> {
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
    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.content_comment, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }



}
