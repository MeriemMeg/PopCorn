
package com.example.meriemmeguellati.cinema.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.example.meriemmeguellati.cinema.TMDBapi.RetrofitCalls.APISeriesCall
import com.example.meriemmeguellati.cinema.TMDBapi.APIresponses.*
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FicheSaisonActivity : AppCompatActivity() {


    var isCommentsShown : Boolean = false
    lateinit var saison : Saison

    lateinit var more : ImageButton
    lateinit var showComments : TextView
    private var apiCall: Call<SeasonDetailsResponse>? = null
    private  var apiCallPersons : Call<CreditsResponse>? = null
    private  var apiCallComments : Call<ReviewsResponse>? = null
    private val apiUser = APISeriesCall()
    lateinit var episodesAdapter :  RecyclerViewEpisodesAdapter
    lateinit var  PersonnesLieesAdapter : RecyclerViewPersonnesAdapter
    lateinit var episodes_recycler_view :RecyclerView
    lateinit var personnesLieesRecycler_view : RecyclerView
    lateinit var followItem : MenuItem
    var comments  = ArrayList<Comment>()
    lateinit var backdrop : ImageView
    lateinit var description :TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_film_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.title=""

        val intent = intent
        this.saison = intent.getSerializableExtra("saison") as Saison

        val titre = findViewById<TextView>(R.id.film_name)
        titre.text = this.saison.serie+ " S" + this.saison.num




         description = findViewById<TextView>(R.id.film_description)

        backdrop = findViewById(R.id.backdrop_film)
        Glide.with(baseContext)
                .load(BuildConfig.BASE_URL_IMG + "w780" + saison.backdrop_path)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultfiche)
                        .centerCrop()
                )
                .into(backdrop)



        personnesLieesRecycler_view = findViewById<RecyclerView>(R.id.personnes_associees)

        loadAssociatedPersons(saison.id,this)

        episodes_recycler_view = findViewById<RecyclerView>(R.id.film_lies)

        loadEpisodes(saison.id,this)

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
        if (apiCall != null) apiCall!!.cancel()
        if(apiCallPersons != null) apiCallPersons!!.cancel()
        if(apiCallComments != null) apiCallComments!!.cancel()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_follow -> {
            this.saison.suivre();
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


    private fun loadEpisodes(serieId: Int,context : Context) {
        apiCall = apiUser.getService().getSeasonDetails(serieId, saison.num)
        apiCall!!.enqueue(object : Callback<SeasonDetailsResponse> {
            override fun onResponse(call: Call<SeasonDetailsResponse>, response: Response<SeasonDetailsResponse>) {
                if (response.isSuccessful()) {

                    val items = response.body()!!

                    description.text = items.overview
                    var episode : Episode

                    for (item in items.episodes!!){
                        episode = Episode(saison.serie?:"Aucun titre n'est disponible",saison.num, item.episode_number?:0, R.drawable.defaultposter,R.drawable.defaultposter, "")
                        episode.id = item.id
                        episode.still_path = item.still_path?:""
                        episode.networks = saison.networks
                        episode.description = item.overview
                        saison.episodes.add(episode)
                        var p :Personne
                        if (item.episode_number == 1){
                            for (person in item.guest_stars!!){
                                p = Personne(person.name!!,"19/12/1983",R.drawable.jenniferlawrence,R.drawable.jenniferlawrence, person.character!!)
                                p.profil = person.profile_path?:""
                                p.id = person?.id?:0
                                saison.personnages.add(p)
                                PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(context, saison.personnages,"online")

                                personnesLieesRecycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                                personnesLieesRecycler_view.adapter = PersonnesLieesAdapter
                            }


                        }
                    }

                    //
                    episodes_recycler_view.setHasFixedSize(true)
                    //
                    episodesAdapter = RecyclerViewEpisodesAdapter(context, saison.episodes)

                    episodes_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    episodes_recycler_view.adapter = episodesAdapter

                } else
                    Toast.makeText(context, response.message() , Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<SeasonDetailsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadAssociatedPersons(serieId: Int,context : Context) {
        apiCallPersons = apiUser.getService().getAssociatedPersons(serieId)
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
                        saison.personnages.add(p)
                    }

                    PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(context, saison.personnages,"online")

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




    private fun loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show()
    }
}
