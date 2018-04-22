package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.R
import android.net.Uri
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.widget.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper
import com.example.meriemmeguellati.cinema.R.styleable.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*


class FicheFilmActivity : AppCompatActivity() {

    var isCommentsShown : Boolean = false
    var estEnCoursDeProjection : Boolean = false
    lateinit var film : Film
    lateinit var data : Data
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_film_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        //getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
       // getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        getSupportActionBar()!!.title=""

        this.data = Data(resources)
        this.data.createComments()

        val intent = intent
         this.film = intent.getSerializableExtra("film") as Film
        this.estEnCoursDeProjection = this.film.estEnCoursDeProjection


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



        val description = findViewById<TextView>(R.id.film_description)
        description.text = film.description

        this.showComments = findViewById<TextView>(R.id.nb_comments)
        this.showComments.text = "Commentaires (4)"

        this.more = findViewById<ImageButton>(R.id.more)

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
        initNavigationDrawer()

        //évènements du Click
        more.setOnClickListener {
            if(this.isCommentsShown ==false) {
                val fragment =  CommentsFragment()
                val bundle = Bundle()
                for (i in 0..(this.data.commentaire.size-1)){
                    bundle.putSerializable("commentaire"+i.toString(),this.data.commentaire[i])
                }
                bundle.putInt("size",this.data.commentaire.size)

                fragment.setArguments(bundle)
                showFragment(fragment)
                this.more.setImageResource(R.drawable.ic_expand_less_black_24dp)
                this.isCommentsShown = true
            }
            else {
                hideFragment()
                this.more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                this.isCommentsShown = false
            }

        }

        playStop.setOnClickListener {
            videoView.start()
           // getSupportActionBar()!!.hide()
            playStop.setVisibility(View.INVISIBLE);
            videoView.setBackgroundResource(0)
            titre.setVisibility(View.INVISIBLE)

        }

        videoView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                videoView.pause()
               // getSupportActionBar()!!.show()
                playStop.setVisibility(View.VISIBLE);
                titre.setVisibility(View.VISIBLE)

                return false
            }
        })


    }

    // Find ID corresponding to the name of the video (in the directory raw).
    fun getRawResIdByName(resName: String): Int {
        val pkgName = this.packageName
        // Return 0 if not found.
        val resID = this.resources.getIdentifier(resName, "raw", pkgName)
        Log.i("AndroidVideoView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_film, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_projection -> {
            if(this.estEnCoursDeProjection ) {
                showSalle()
            }
            else {
                val toast = Toast.makeText(getApplicationContext(),"Le film "+this.film.titre+" n'est projeté dans aucune salle cinéma actuellement", Toast.LENGTH_SHORT)
                toast.show()
            }
            true
        }

        R.id.action_follow -> {
            this.film.suivre();
            initNavigationDrawer()
            true
        }

        R.id.action_rate -> {
            showEvaluation()
            true
        }

        R.id.action_comment -> {

            showCommenter()
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

    private fun hideFragment () {
        getSupportFragmentManager()
                .beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.content_comment)).commit()
    }
    fun showSalle(){
        var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        var mView: View = layoutInflater.inflate(R.layout.salles_projection, null)

        var recyclerView : RecyclerView = mView.findViewById(R.id.salle_projection)
        recyclerView.layoutManager = GridLayoutManager(mView.context, 1)
        val salles = prepareSalles()
        val adapter = SallesProjAdapter(mView.context, salles)

        recyclerView.adapter = adapter

        mBuilder.setView(mView)
        var dialog: AlertDialog = mBuilder.create()
        dialog.show()
        //dialog.closeOptionsMenu()
    }

    private fun prepareSalles() : ArrayList<Salle>{
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
    fun showCommenter(){
        var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        var mView: View = layoutInflater.inflate(R.layout.commenter, null)

        val commentEditText = mView.findViewById<EditText>(R.id.commentText);

        mBuilder.setView(mView)
        var dialog: AlertDialog = mBuilder.create()
        dialog.show()

        val close : Button = mView?.findViewById<Button>(R.id.closePop) as Button
        close.setOnClickListener {view ->
            dialog.cancel()
        }

        val commenter: Button = mView?.findViewById<Button>(R.id.commenter)
        commenter.setOnClickListener{ view ->
            val comment: String = commentEditText.text.toString()
            val myComment = Comment(resources.getStringArray(R.array.comment_1)[0].toInt(),
                    resources.getStringArray(R.array.comment_1)[1],
                    comment, R.drawable.avatar,"Meguellati Ahmed",0)
            this.data.commentaire.add(0,myComment)
            this.showComments.text = "Commentaires ("+this.data.commentaire.size.toString()+")"
            Toast.makeText(this, "votre commentaire a été ajouté" , Toast.LENGTH_LONG).show();
            dialog.cancel()
            if(this.isCommentsShown ) {
                hideFragment()
                this.more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                this.isCommentsShown = false
            }

        }
    }

    fun showEvaluation(){
        var mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        var mView: View = layoutInflater.inflate(R.layout.evaluation, null)

        var ratingBar: RatingBar = mView.findViewById<RatingBar>(R.id.stars);
        mBuilder.setView(mView)
        var dialog: AlertDialog = mBuilder.create()
        dialog.show()

        val close : Button = mView?.findViewById<Button>(R.id.closePop)
        close.setOnClickListener {view ->
            dialog.cancel()
        }

        val evaluer : Button = mView?.findViewById<Button>(R.id.submit)
        evaluer.setOnClickListener {view ->
            val note: Float = ratingBar.getRating()
            val myComment = Comment(resources.getStringArray(R.array.comment_1)[0].toInt(),
                    resources.getStringArray(R.array.comment_1)[1],
                    "", R.drawable.avatar,"Meguellati Ahmed",note.toInt())
            this.data.commentaire.add(0,myComment)
            this.showComments.text = "Commentaires ("+this.data.commentaire.size.toString()+")"
            Toast.makeText(this, "votre évaluation a été ajoutée" , Toast.LENGTH_LONG).show();
            if(this.isCommentsShown ) {
                hideFragment()
                this.more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                this.isCommentsShown = false
            }
            dialog.cancel()
        }
    }

    fun initNavigationDrawer() {
        //views
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navDrawerHelper =  NavDrawerHelper(this);
       // val data = Data(resources)
       // data.createData()
         if (this.film.estSuivi) navDrawerHelper.setFanFilms(this.film)
        navDrawerHelper.initNav(drawerLayout, navigationView, false);
    }

}
