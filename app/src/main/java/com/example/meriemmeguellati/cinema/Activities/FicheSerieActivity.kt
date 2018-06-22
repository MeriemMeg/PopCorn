package com.example.meriemmeguellati.cinema.Activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.*
import com.example.meriemmeguellati.cinema.APIresponses.*
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FicheSerieActivity : AppCompatActivity() {

    var isCommentsShown : Boolean = false
    lateinit var serie : Serie
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    private var apiCall: Call<LatestSeriesResponse>? = null
    private  var apiCallSeasons : Call<SerieDetailsResponse>? = null
    private  var apiCallComments : Call<ReviewsResponse>? = null
    private val apiUser = APISeriesCall()
    lateinit var seriesLiesAdapter :  RecyclerViewSeriesLieesAdapter
    lateinit var  saisonsAdapter : RecyclerViewSaisonAdapter
    lateinit var series_liées_recycler_view :RecyclerView
    lateinit var saisonsRecycler_view : RecyclerView
    lateinit var followItem : MenuItem

    lateinit var backdrop : ImageView
    var comments  = ArrayList<Comment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_serie_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.title=""


        val intent = intent
        this.serie = intent.getSerializableExtra("serie") as Serie

        series_liées_recycler_view = findViewById<RecyclerView>(R.id.series_liees)
        loadSimilarSeries(this.serie.id)

        saisonsRecycler_view = findViewById<RecyclerView>(R.id.saisons)
        loadSaisons(this.serie.id)



        val name = findViewById<TextView>(R.id.serie_name)
        name.text = this.serie.titre

        backdrop = findViewById(R.id.backdrop)

        val description = findViewById<TextView>(R.id.series_descrp)
        description.text = this.serie.description

        val affiche = findViewById<FrameLayout>(R.id.affiche)
        affiche.setBackgroundResource(this.serie.poster)

        this.showComments = findViewById<TextView>(R.id.nb_comments)
        this.showComments.text = "Commentaires (4)"
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
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

        R.id.action_follow -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            this.serie.suivre()
            initNavigationDrawer()
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

      /*  val commenter: Button = mView?.findViewById<Button>(R.id.commenter)
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

        }*/
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

      /*  val evaluer : Button = mView?.findViewById<Button>(R.id.submit)
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
        } */
    }

    fun initNavigationDrawer() {
        //views
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navDrawerHelper =  NavDrawerHelper(this);
        // val data = Data(resources)
        // data.createData()
        if (this.serie.estSuivi) navDrawerHelper.setFanSeries(this.serie)
        navDrawerHelper.initNav(drawerLayout, navigationView, false);
    }


    private fun loadSimilarSeries(serie_item: Int) {
        apiCall = apiUser.getService().getSimilarSeries(serie_item, Language().Country())
        apiCall!!.enqueue(object : Callback<LatestSeriesResponse> {
            override fun onResponse(call: Call<LatestSeriesResponse>, response: Response<LatestSeriesResponse>) {
                if (response.isSuccessful()) {

                    val items = response.body()!!.getResults()!!
                    var filmsLiees = ArrayList<Film>()
                    var s : Serie
                    for (item in items ){
                        s = Serie(item?.name?:"Aucun titre n'est disponible", R.drawable.p1, item?.overview?:"Aucune description n'est disponible", R.drawable.p1)
                        s.id = item.id
                        s.posterPath = item?.poster_path?:""
                        serie.seriesLiees.add(s)
                    }

                    series_liées_recycler_view.setHasFixedSize(true)
                    seriesLiesAdapter = RecyclerViewSeriesLieesAdapter(baseContext, serie.seriesLiees)
                    series_liées_recycler_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

                    series_liées_recycler_view.adapter = seriesLiesAdapter
                    //


                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<LatestSeriesResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadSaisons(serieId: Int) {
        apiCallSeasons = apiUser.getService().getDetailSerie(serieId,Language().Country())
        apiCallSeasons!!.enqueue(object : Callback<SerieDetailsResponse> {
            override fun onResponse(call: Call<SerieDetailsResponse>, response: Response<SerieDetailsResponse>) {
                if (response.isSuccessful()) {

                    val item = response.body()
                    serie.backdrop_path = item?.backdrop_path?:""
                    serie.networks = item?.networks
                    Glide.with(baseContext)
                            .load(BuildConfig.BASE_URL_IMG + "w300" + serie.backdrop_path)
                            .apply(RequestOptions()
                                    .placeholder(R.drawable.revenge)
                                    .centerCrop()
                            )
                            .into(backdrop)
                    val cast= item!!.seasons
                    var s : Saison
                    for (saison in cast!!){
                        s = Saison(serie?.titre?:"Aucun Nom", saison?.season_number?:0, R.drawable.jenniferlawrence, "", R.drawable.jenniferlawrence,"")
                        s.poster_path = saison?.poster_path?:""
                        s.id = serie.id
                        s.networks = serie.networks
                        serie.saisons.add(s)
                    }

                    saisonsRecycler_view.setHasFixedSize(true)
                    saisonsAdapter = RecyclerViewSaisonAdapter(baseContext, serie.saisons)

                    saisonsRecycler_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

                    saisonsRecycler_view.adapter = saisonsAdapter



                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<SerieDetailsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadComments(serieid: Int) {
        apiCallComments = apiUser.getService().getComments(serieid)
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

}
