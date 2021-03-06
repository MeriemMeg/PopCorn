package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewFilmLiesAdapter
import com.example.meriemmeguellati.cinema.R
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APIpersonnesCall
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FichePersonnesActivity : AppCompatActivity() {


    var isCommentsShown : Boolean = false
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    lateinit var personne : Personne
    private var apiCallPersons: Call<PersonneCastResponse>? = null
    private  var apiCallDetails : Call<PersonDetailsResponse>? = null
   private  var apiCallComments : Call<ReviewsResponse>? = null
    private val apiUser = APIpersonnesCall()
    lateinit var naissance :TextView
    lateinit var description :TextView
    lateinit var film_liées_recycler_view : RecyclerView
    lateinit var filmographieAdapter : RecyclerViewFilmLiesAdapter
    var comments = ArrayList<Comment>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_personne_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.title=""



        val intent = intent
        personne = intent.getSerializableExtra("Personne") as Personne

        val nomActeur = findViewById<TextView>(R.id.nomActeur)
        nomActeur.text = personne.nom

        naissance = findViewById<TextView>(R.id.bearthday)

        val backdrop = findViewById<ImageView>(R.id.profileImage)


        val background = findViewById<FrameLayout>(R.id.film_background)
        //background.setBackgroundResource(personne.photo)
        if(personne.gender == 1)
        Glide.with(baseContext)
                .load(BuildConfig.BASE_URL_IMG + "w300" + personne.profil)
                .apply(RequestOptions()
                        .placeholder(R.drawable.femmeholder)
                        .centerCrop()
                )
                .into(backdrop)
        else
            Glide.with(baseContext)
                    .load(BuildConfig.BASE_URL_IMG + "w500" + personne.profil)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.hommeholder)
                            .centerCrop()
                    )
                    .into(backdrop)

       description = findViewById<TextView>(R.id.film_description)

        loadPersonneDetails(personne.id)

        film_liées_recycler_view = findViewById<RecyclerView>(R.id.filmgraphie)

        loadFilmography(personne.id,this)

        this.showComments = findViewById<TextView>(R.id.nb_comments)
        this.showComments.text = "Commentaires (0)"
        this.more = findViewById<ImageButton>(R.id.more)
        initNavigationDrawer()
        //évènements du Click
        more.setOnClickListener {
            if(this.isCommentsShown ==false) {
                val fragment =  CommentsFragment()
                val bundle = Bundle()
                for (i in 0..(this.comments.size-1)){
                    bundle.putSerializable("commentaire"+i.toString(),this.comments[i])
                }
                bundle.putInt("size",this.comments.size)

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


    }

    override fun onDestroy() {
        super.onDestroy()
        if (apiCallDetails != null) apiCallDetails!!.cancel()
        if(apiCallPersons != null) apiCallPersons!!.cancel()
        if(apiCallComments != null) apiCallComments!!.cancel()


    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_personne, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_comment -> {
            showCommenter()
            true
        }

        R.id.action_rate -> {
            showEvaluation()
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
        val fragmentManager = supportFragmentManager
        getSupportFragmentManager()
                .beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.content_comment)).commit()
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
            comments.add(0,myComment)
            this.showComments.text = "Commentaires ("+comments.size.toString()+")"
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
        navDrawerHelper.initNav(drawerLayout, navigationView, false);
    }
    private fun loadFilmography(personneId: Int,context : Context) {
        apiCallPersons = apiUser.getService().getMoviesPerson(personneId,Language().Country())
        apiCallPersons!!.enqueue(object : Callback<PersonneCastResponse> {
            override fun onResponse(call: Call<PersonneCastResponse>, response: Response<PersonneCastResponse>) {
                if (response.isSuccessful()) {

                    val items = response.body()!!
                    var film : Film
                    for (item in items.cast!!){
                        film = Film(item?.title?:"Aucun titre n'est disponible","", item?.overview?:"Aucune description n'est disponible", item?.posterPath?:"", R.drawable.defaultposter)
                        film.id = item.id
                        film.backdrop_path = item.backdropPath?:""
                        personne.filmographie.add(film)
                    }

                    film_liées_recycler_view.setHasFixedSize(true)
                    //
                    filmographieAdapter = RecyclerViewFilmLiesAdapter(context, personne.filmographie,"online")

                    film_liées_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    film_liées_recycler_view.adapter = filmographieAdapter



                } else
                    Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<PersonneCastResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadPersonneDetails(personneId: Int) {
        apiCallDetails = apiUser.getService().getDetailPerson(personneId,Language().Country())
        apiCallDetails!!.enqueue(object : Callback<PersonDetailsResponse> {
            override fun onResponse(call: Call<PersonDetailsResponse>, response: Response<PersonDetailsResponse>) {
                if (response.isSuccessful()) {

                    val item = response.body()
                    personne.biographie = item?.biography?:"Aucune biographie"
                    personne.naissance = item?.birthday?:"Aucune"
                    naissance.text = personne.naissance
                    description.text = personne.biographie

                } else
                    Toast.makeText(baseContext, response.message() , Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<PersonDetailsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }


    private fun loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }
}
