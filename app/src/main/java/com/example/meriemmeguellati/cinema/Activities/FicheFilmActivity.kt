package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.R
import android.os.AsyncTask
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper
import com.example.meriemmeguellati.cinema.OfflineData.FilmDB
import com.example.meriemmeguellati.cinema.OfflineData.FilmEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FicheFilmActivity : AppCompatActivity() {

    var isCommentsShown : Boolean = false
    var estEnCoursDeProjection : Boolean = false
    lateinit var film : Film
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    private var apiCall: Call<NowPlayingResponse>? = null
    private  var apiCallPersons : Call<CreditsResponse>? = null
    private  var apiCallComments : Call<ReviewsResponse>? = null
    private val apiUser = APImoviesCall()
    lateinit var filmsLiesAdapter :  RecyclerViewFilmLiesAdapter
    lateinit var  PersonnesLieesAdapter : RecyclerViewPersonnesAdapter
    lateinit var film_liées_recycler_view :RecyclerView
    lateinit var personnesLieesRecycler_view : RecyclerView
    lateinit var followItem : MenuItem
    var comments  = ArrayList<Comment>()
    lateinit var backdrop : ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_film_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        getSupportActionBar()!!.title=""


        val intent = intent
         this.film = intent.getSerializableExtra("film") as Film
        this.estEnCoursDeProjection = this.film.estEnCoursDeProjection

        backdrop = findViewById(R.id.backdrop_film)
        Glide.with(baseContext)
                .load(BuildConfig.BASE_URL_IMG + "w300" + film.backdrop_path)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultfiche)
                        .centerCrop()
                )
                .into(backdrop)


        val titre = findViewById<TextView>(R.id.film_name)
        titre.text = film.titre

        val playStop = findViewById<ImageButton>(R.id.play_stop)
        playStop.setImageResource(R.drawable.ic_play_arrow_white_24dp)

        val background = findViewById<FrameLayout>(R.id.film_background)
       // background.setBackgroundResource(film.affiche)

        var videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

      /*  try {
            // ID of video file.
            val id = this.getRawResIdByName(film.posterPath)
            videoView.setVideoURI(Uri.parse("android.resource://$packageName/$id"))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }*/
        videoView.setBackgroundResource(R.drawable.defaultfiche)

      //  videoView.requestFocus()



        val description = findViewById<TextView>(R.id.film_description)
        description.text = film.description
        this.showComments = findViewById<TextView>(R.id.nb_comments)
        this.showComments.text = "Commentaires..."
        this.more = findViewById<ImageButton>(R.id.more)
        personnesLieesRecycler_view = findViewById<RecyclerView>(R.id.personnes_associees)
        personnesLieesRecycler_view.setHasFixedSize(true)
        loadAssociatedPersons(film.id.toString(),this)
        loadComments(film.id.toString())
        film_liées_recycler_view = findViewById<RecyclerView>(R.id.film_lies)
        film_liées_recycler_view.setHasFixedSize(true)
        loadSimilarMovies(film.id.toString(),this)
        initNavigationDrawer()

        //évènements du Click

        more.setOnClickListener {
            if(isCommentsShown ==false) {
                val fragment =  CommentsFragment()
                val bundle = Bundle()
                for (i in 0..(comments.size-1)){
                    bundle.putSerializable("commentaire"+i.toString(),comments[i])
                    //Toast.makeText(this, comments[i].message, Toast.LENGTH_SHORT).show()
                }
                bundle.putInt("size",comments.size)

                fragment.setArguments(bundle)
                showFragment(fragment)
                more.setImageResource(R.drawable.ic_expand_less_black_24dp)
                isCommentsShown = true
            }
            else {
                hideFragment()
                more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                isCommentsShown = false
            }

        }

/*
        playStop.setOnClickListener {
            videoView.start()
            playStop.setVisibility(View.INVISIBLE);
            videoView.setBackgroundResource(0)
            titre.setVisibility(View.INVISIBLE)
        }

        videoView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                videoView.pause()
                playStop.setVisibility(View.VISIBLE);
                titre.setVisibility(View.VISIBLE)
                return false
            }
        })
 */


    }

   /* // Find ID corresponding to the name of the video (in the directory raw).
    fun getRawResIdByName(resName: String): Int {
        val pkgName = this.packageName
        val resID = this.resources.getIdentifier(resName, "raw", pkgName)
        Log.i("AndroidVideoView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }*/

    override fun onDestroy() {
        super.onDestroy()
        if (apiCall != null) apiCall!!.cancel()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_film, menu)
        this.followItem = menu.getItem(1)
        this.followItem.setEnabled(false)
       // var exist : Boolean = false
        checkFilm( this)
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
            if(film.estSuivi){

                item.icon = getDrawable(R.drawable.baseline_favorite_border_white_18dp)
                unfavorise()
            }
            else {
                item.icon = getDrawable(R.drawable.ic_favorite_white_24dp)
                saveFilm()
            }

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
            this.comments.add(0,myComment)
            this.showComments.text = "Commentaires ("+this.comments.size.toString()+")"
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
            comments.add(0,myComment)
            this.showComments.text = "Commentaires ("+comments.size.toString()+")"
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
         if (this.film.estSuivi) navDrawerHelper.setFanFilms(this.film)
        navDrawerHelper.initNav(drawerLayout, navigationView, false);
    }

    private fun loadSimilarMovies(movie_item: String,contect : Context) {
        apiCall = apiUser.getService().getSimilarmovies(movie_item, Language().Country())
        apiCall!!.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                if (response.isSuccessful()) {

                    val items = response.body()!!.getResults()!!
                    var filmsLiees = ArrayList<Film>()
                    var film : Film
                    for (item in items ){
                        film = Film(item?.title?:"Aucun titre n'est disponible", R.drawable.p1, item?.overview?:"Aucune description n'est disponible", item?.posterPath?:"", R.drawable.p1)
                        film.id = item.id
                        filmsLiees.add(film)
                    }

                    //
                    filmsLiesAdapter = RecyclerViewFilmLiesAdapter(contect, filmsLiees)

                    film_liées_recycler_view.layoutManager = LinearLayoutManager(contect, LinearLayoutManager.VERTICAL, false)

                    film_liées_recycler_view.adapter = filmsLiesAdapter

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadAssociatedPersons(movie_item: String,context: Context) {
        apiCallPersons = apiUser.getService().getAssociatedPersons(movie_item)
        apiCallPersons!!.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(call: Call<CreditsResponse>, response: Response<CreditsResponse>) {
                if (response.isSuccessful()) {

                    val item = response.body()
                    val cast= item!!.cast
                    var personnesLiees = ArrayList<Personne>()
                    var p : Personne
                    for (person in cast!!){
                        p = Personne(person?.name?:"Aucun Nom", "12/2/1978", R.drawable.jenniferlawrence, R.drawable.jenniferlawrence, "biooooooooooooographie")
                        if(person.profile_path != null )p.profil = person.profile_path!!
                        p.id = person?.id?:0
                        p.gender = person?.gender?:1
                        personnesLiees.add(p)
                    }

                    PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(context, personnesLiees)

                    personnesLieesRecycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    personnesLieesRecycler_view.adapter = PersonnesLieesAdapter

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadComments(movie_item: String) {
        apiCallComments = apiUser.getService().getComments(movie_item)
        apiCallComments!!.enqueue(object : Callback<ReviewsResponse> {
            override fun onResponse(call: Call<ReviewsResponse>, response: Response<ReviewsResponse>) {
                if (response.isSuccessful()) {

                    val item = response.body()
                    var comment : Comment

                        for (c in item?.results!!){
                             comment = Comment(
                                     0,
                                     "",
                                     c?.content?:"aucun contenu n'est disponible",
                                     R.drawable.jamescorden,
                                     c?.author?:"no name",
                                     3)
                            comments.add(comment)
                        }

                    showComments.text = "Commentaires ("+comments.size.toString()+")"


                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }

    private fun saveFilm(){
        var act = this
        val titre = this.film.titre
        val description = this.film.description
        val trailer = this.film.posterPath
        val trailerposter: Int = this.film.trailerposter
        val affiche: Int = this.film.affiche
        val id : Int = this.film.id


        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = FilmDB.getInstance(act)
                val dao = db?.FilmDAO()

                    val film = FilmEntity(id,titre,affiche,description,trailer,trailerposter)
                    dao?.ajouter(film)

                return null
            }


            override fun onPostExecute(result: Void?) {
                    film.estSuivi = true
                    val toast = Toast.makeText(applicationContext,"Le film est ajouté à votre favorie", Toast.LENGTH_SHORT)
                    toast.show()

            }
        }.execute()

    }

    fun checkFilm(context : Activity) {

        var act = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = FilmDB.getInstance(act)
                val dao = db?.FilmDAO()
                val existing = dao?.getFilm(film.id)
                if(existing != film.id){
                    film.estSuivi = false
                           context.runOnUiThread(java.lang.Runnable {

                            followItem.icon = getDrawable(R.drawable.baseline_favorite_border_white_18dp)
                            followItem.setEnabled(true)

                    })

                }
                else {
                    film.estSuivi = true

                    context.runOnUiThread(java.lang.Runnable {


                        followItem.icon = getDrawable(R.drawable.ic_favorite_white_24dp)
                        followItem.setEnabled(true)

                    })



                }

                return null
            }


            override fun onPostExecute(result: Void?) {


            }

        }.execute()


    }
    fun unfavorise() {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = FilmDB.getInstance(baseContext)
                val dao = db?.FilmDAO()
                dao?.supprimer(film.id)
                return null
            }


            override fun onPostExecute(result: Void?) {
                film.estSuivi = false
                val toast = Toast.makeText(applicationContext,"Le film est retiré de votre favorie", Toast.LENGTH_SHORT)
                toast.show()

            }
        }.execute()
    }

}
