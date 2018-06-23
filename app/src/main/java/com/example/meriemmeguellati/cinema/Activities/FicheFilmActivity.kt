package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.R
import android.os.AsyncTask
import android.os.Build
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APImoviesCall
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.Animation.ShadowTransformer
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper
import com.example.meriemmeguellati.cinema.Notifications.NotificationCreator
import com.example.meriemmeguellati.cinema.OfflineData.FilmAssocieEntity
import com.example.meriemmeguellati.cinema.OfflineData.FilmDB
import com.example.meriemmeguellati.cinema.OfflineData.FilmEntity
import com.example.meriemmeguellati.cinema.OfflineData.PersonneEntity
import com.google.android.gms.common.images.internal.LoadingImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.net.URL


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
    lateinit var personnes : ArrayList<Personne>
    lateinit var filmsAssocies: ArrayList<Film>
    var filmsLiees = ArrayList<Film>()
    var personnesLiees = ArrayList<Personne>()

    private val apiMovies = APImoviesCall()
    private var apiVediosCall: Call<VideoResponse>? = null

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_film_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        getSupportActionBar()!!.title=""


        val intent = intent
        var mode = intent.getStringExtra("mode")
        if(intent.getSerializableExtra("NotificationFilm") != null){

            this.film = intent.getSerializableExtra("NotificationFilm") as Film
            mode = "online"
            loadNotifFilmDetails(film.id.toString())
        }
         else this.film = intent.getSerializableExtra("film") as Film
        this.estEnCoursDeProjection = this.film.estEnCoursDeProjection




        val titre = findViewById<TextView>(R.id.film_name)
        titre.text = film.titre




        val description = findViewById<TextView>(R.id.film_description)
        description.text = film.description

        backdrop = findViewById(R.id.backdrop_film)

        if (mode == "offline") {
           if (intent.getSerializableExtra("personnes") != null) this.personnes = intent.getSerializableExtra("personnes") as ArrayList<Personne>
            else this.personnes = ArrayList<Personne>()
            if (intent.getSerializableExtra("filmsAssocies") != null)this.filmsAssocies = intent.getSerializableExtra("filmsAssocies") as ArrayList<Film>
            else this.filmsAssocies = ArrayList<Film>()
            preparePersonnage(this)
            prepareFilmsAssociés(this)
            var poster = loadImage("MOV"+film.titre)
            backdrop.setImageBitmap(poster)


        }else {


            Glide.with(baseContext)
                    .load(BuildConfig.BASE_URL_IMG + "w780" + film.backdrop_path)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.defaultfiche)
                            .centerCrop()
                    )
                    .into(backdrop)


            this.showComments = findViewById<TextView>(R.id.nb_comments)
            this.showComments.text = "Commentaires..."
            this.more = findViewById<ImageButton>(R.id.more)
            personnesLieesRecycler_view = findViewById<RecyclerView>(R.id.personnes_associees)
            personnesLieesRecycler_view.setHasFixedSize(true)
           // val toast = Toast.makeText(applicationContext,"hello again", Toast.LENGTH_SHORT)
           // toast.show()

            loadAssociatedPersons(film.id.toString(), this)
            loadComments(film.id.toString())
            film_liées_recycler_view = findViewById<RecyclerView>(R.id.film_lies)
            film_liées_recycler_view.setHasFixedSize(true)
            loadSimilarMovies(film.id.toString(), this)
            initNavigationDrawer()

            //évènements du Click

            more.setOnClickListener {
               if (isCommentsShown == false) {
                    val fragment = CommentsFragment()
                    val bundle = Bundle()
                    for (i in 0..(comments.size - 1)) {
                        bundle.putSerializable("commentaire" + i.toString(), comments[i])
                        //Toast.makeText(this, comments[i].message, Toast.LENGTH_SHORT).show()
                    }
                    bundle.putInt("size", comments.size)

                    fragment.setArguments(bundle)
                    showFragment(fragment)
                    more.setImageResource(R.drawable.ic_expand_less_black_24dp)
                    isCommentsShown = true
                } else {
                    hideFragment()
                    more.setImageResource(R.drawable.ic_expand_more_black_24dp)
                    isCommentsShown = false
                }


            }



  }

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
          /*  if(this.estEnCoursDeProjection ) {
                showSalle()
            }
            else {
                val toast = Toast.makeText(getApplicationContext(),"Le film "+this.film.titre+" n'est projeté dans aucune salle cinéma actuellement", Toast.LENGTH_SHORT)
                toast.show()
            }*/

            startYoutubeIntent(this,film.id)
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
                for (i in 0..(personnesLiees.size-1)){
                    savePersonne(i)
                    Toast.makeText(baseContext, (i+1).toString()+"/"+personnesLiees.size, Toast.LENGTH_LONG).show()
                }
                for (j in 0..(filmsLiees.size-1)){
                    saveFilmsAssocies(j)
                    Toast.makeText(baseContext, (j+1).toString()+"/"+filmsAssocies.size, Toast.LENGTH_LONG).show()
                }
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

                    var film : Film
                    for (item in items ){
                        film = Film(item?.title?:"Aucun titre n'est disponible", "", item?.overview?:"Aucune description n'est disponible", item?.posterPath?:"", R.drawable.defaultposter)
                        film.id = item.id
                        filmsLiees.add(film)
                    }

                    //
                    filmsLiesAdapter = RecyclerViewFilmLiesAdapter(contect, filmsLiees,"online")

                    film_liées_recycler_view.layoutManager = LinearLayoutManager(contect, LinearLayoutManager.VERTICAL, false)

                    film_liées_recycler_view.adapter = filmsLiesAdapter

                } else
                    Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadNotifFilmDetails(id:String){
        var apiMoviesCall: Call<MovieDetailResponse>? = null
        val apiMovies = APImoviesCall()
        apiMoviesCall = apiMovies.getService().getDetailMovie(id,Language().Country())
        apiMoviesCall!!.enqueue(object : Callback<MovieDetailResponse> {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if (response.isSuccessful) {

                    val item = response.body()


                    if (item != null) {
                        film.backdrop_path = item.backdropPath?:""
                        film.id = item.id
                        film.description = item.overview!!




                    }

                } else
                    Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();


            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                //loadFailed()
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
                    var p : Personne
                    for (person in cast!!){
                        p = Personne(person?.name?:"Aucun Nom", "12/2/1978", R.drawable.jenniferlawrence, R.drawable.jenniferlawrence, "biooooooooooooographie")
                        if(person.profile_path != null )p.profil = person.profile_path!!
                        p.id = person?.id?:0
                        p.gender = person?.gender?:1
                        personnesLiees.add(p)
                    }

                    PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(context, personnesLiees,"online")

                    personnesLieesRecycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    personnesLieesRecycler_view.adapter = PersonnesLieesAdapter

                } else
                    Toast.makeText(context, response.message() , Toast.LENGTH_LONG).show();
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
                    Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();
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
       // val affiche: Int = this.film.affiche

        val affiche = java.util.UUID.randomUUID().toString()
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
                saveImage(BuildConfig.BASE_URL_IMG + "w300" + film.backdrop_path, "MOV"+titre)

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

    fun preparePersonnage(context: Context){
        personnesLieesRecycler_view = findViewById(R.id.personnes_associees)
        personnesLieesRecycler_view.setHasFixedSize(true)

        PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(context, personnes,"offline")

        personnesLieesRecycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        personnesLieesRecycler_view.adapter = PersonnesLieesAdapter
    }

    fun prepareFilmsAssociés(context : Context){
        film_liées_recycler_view = findViewById(R.id.film_lies)
        film_liées_recycler_view.setHasFixedSize(true)

        filmsLiesAdapter = RecyclerViewFilmLiesAdapter(context, filmsAssocies,"offline")

        film_liées_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        film_liées_recycler_view.adapter = filmsLiesAdapter
    }


    private fun saveFilmsAssocies(i: Int){
        var act = this
        val id : Int = this.filmsLiees[i].id
        val film_id : Int = this.film.id
        val titre = this.filmsLiees[i].titre
        val affiche = this.filmsLiees[i].affiche

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = FilmDB.getInstance(act)
                val dao = db?.FilmAssocieDAO()


                val existing = dao?.getFilm(id)
                if(existing != id){
                    val film = FilmAssocieEntity(id,film_id,titre, affiche)
                    dao?.insert(film)
                }




                return null
            }


            override fun onPostExecute(result: Void?) {

                if(filmsLiees[i].posterPath != null && filmsLiees[i].posterPath!="")
                saveImage(BuildConfig.BASE_URL_IMG + "w154" + filmsLiees[i].posterPath, "MOVA_" + titre)

            }
        }.execute()

    }

    private fun savePersonne(i: Int){
        var act = this
        val id : Int = this.personnesLiees[i].id
        val film_id : Int = this.film.id
        val nom = this.personnesLiees[i].nom
        val profil = this.personnesLiees[i].profil

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = FilmDB.getInstance(act)
                val dao = db?.PersonneDAO()

                val existing = dao?.getPersonne(id)
                if(existing != id){
                    val personne = PersonneEntity(id,film_id,nom, profil)
                    dao?.insert(personne)
                }


                return null
            }


            override fun onPostExecute(result: Void?) {

                if(personnesLiees[i].profil != null && personnesLiees[i].profil != "")
                saveImage(BuildConfig.BASE_URL_IMG + "w154" + personnesLiees[i].profil, "PER_" + nom)

            }
        }.execute()

    }

    private class InsertTask// only retain a weak reference to the activity
    internal constructor(context: FicheFilmActivity, private val url: String,private val movie_id:String) : AsyncTask<Void, Void, Boolean>() {

        private var activityReference: WeakReference<FicheFilmActivity> = WeakReference(context)

        // doInBackground methods runs on a worker thread
        override fun doInBackground(vararg objs: Void): Boolean? {
            val url = URL(url)
            val input = url.openStream()
            try {
                //The sdcard directory e.g. '/sdcard' can be used directly, or
                //more safely abstracted with getExternalStorageDirectory()
                val storagePath = Environment.getExternalStorageDirectory()
                println("storage path $storagePath")
                val output = FileOutputStream(File(storagePath, movie_id+".jpg"))
                try {
                    val buffer = ByteArray(2048)
                    var bytesRead = 0
                    bytesRead = input.read(buffer, 0, buffer.size)
                    while ((bytesRead) >= 0) {
                        output.write(buffer, 0, bytesRead)
                        bytesRead = input.read(buffer, 0, buffer.size)
                    }
                } finally {
                    output.close()
                }
            } finally {
                input.close()
            }
            return true
        }

        // onPostExecute runs on main thread
        override fun onPostExecute(bool: Boolean?) {
        }
    }

    fun saveImage(pic_Url: String, image_name: String) {
        val result=
                FicheFilmActivity.InsertTask(
                        this,
                        pic_Url,
                        image_name).execute()
    }

    fun loadImage(image_name:String): Bitmap? {
        val photoPath = Environment.getExternalStorageDirectory().toString() + "/"+image_name+".jpg"
        return BitmapFactory.decodeFile(photoPath)
    }


    fun startYoutubeIntent (context : Context, id : Int){

            apiVediosCall = apiMovies.getService().getVideos(id)
            apiVediosCall!!.enqueue(object : Callback<VideoResponse> {
                override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                    if (response.isSuccessful) {

                        val videos = response.body()!!.results
                        if (videos != null) {
                            var found = false
                            for (video in videos){

                                if(!found && video.site == "YouTube") {
                                    Toast.makeText(applicationContext,"found", Toast.LENGTH_SHORT).show()
                                    found = true
                                    val appIntent = Intent(Intent.ACTION_VIEW,Uri.parse("vnd.youtube:"+video.key))
                                    val webIntent =  Intent(Intent.ACTION_VIEW,Uri.parse("http://youtube.com/watch?v="+video.key))
                                    try {
                                        context.startActivity(appIntent)
                                        //Toast.makeText(applicationContext,"app", Toast.LENGTH_SHORT).show()
                                    } catch ( ex : ActivityNotFoundException) {
                                        context.startActivity(webIntent)
                                       // Toast.makeText(applicationContext,"web", Toast.LENGTH_SHORT).show()
                                    }
                                }

                            }
                        }


                    } else {
                        Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();
                    }

                }

                override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                    loadFailed()
                }
            })

    }



}
