
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
import android.util.Log
import android.view.*
import android.widget.*
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.example.meriemmeguellati.cinema.APISeriesCall
import com.example.meriemmeguellati.cinema.APImoviesCall
import com.example.meriemmeguellati.cinema.APIresponses.*
import com.example.meriemmeguellati.cinema.Adapters.*
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

        val playStop = findViewById<ImageButton>(R.id.play_stop)
       // playStop.setImageResource(R.drawable.ic_play_arrow_white_24dp)

        val background = findViewById<FrameLayout>(R.id.film_background)
        background.setBackgroundResource(this.saison.affiche)
       /* var videoView = findViewById<VideoView>(R.id.videoView) as VideoView
        val mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

        try {
            // ID of video file.
            val id = this.getRawResIdByName(this.saison.trailer)
            videoView.setVideoURI(Uri.parse("android.resource://$packageName/$id"))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }
        videoView.setBackgroundResource(this.saison.poster)

        videoView.requestFocus() */


        val description = findViewById<TextView>(R.id.film_description)
        description.text = this.saison.description


        personnesLieesRecycler_view = findViewById<RecyclerView>(R.id.personnes_associees)

        loadAssociatedPersons(saison.id)

        episodes_recycler_view = findViewById<RecyclerView>(R.id.film_lies)

        loadEpisodes(saison.id)

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




    /*    playStop.setOnClickListener {
            videoView.start()
            //getSupportActionBar()!!.hide()
            playStop.setVisibility(View.INVISIBLE);
            titre.setVisibility(View.INVISIBLE)
            videoView.setBackgroundResource(0)

        }

        videoView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {

                videoView.pause()
                //getSupportActionBar()!!.show()
                playStop.setVisibility(View.VISIBLE);
                titre.setVisibility(View.VISIBLE)
                return false
            }
        })

  */
    }

    override fun onDestroy() {
        super.onDestroy()
        if (apiCall != null) apiCall!!.cancel()
        if(apiCallPersons != null) apiCallPersons!!.cancel()
        if(apiCallComments != null) apiCallComments!!.cancel()


    }

 /*   // Find ID corresponding to the name of the resource (in the directory raw).
    fun getRawResIdByName(resName: String): Int {
        val pkgName = this.packageName
        // Return 0 if not found.
        val resID = this.resources.getIdentifier(resName, "raw", pkgName)
        Log.i("AndroidVideoView", "Res Name: $resName==> Res ID = $resID")
        return resID
    }
*/
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

    /*    val commenter: Button = mView?.findViewById<Button>(R.id.commenter)
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

     /*   val evaluer : Button = mView?.findViewById<Button>(R.id.submit)
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
        }*/
    }
    fun initNavigationDrawer() {
        //views
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navDrawerHelper =  NavDrawerHelper(this);
        navDrawerHelper.initNav(drawerLayout, navigationView, false);
    }


    private fun loadEpisodes(serieId: Int) {
        apiCall = apiUser.getService().getSeasonDetails(serieId, saison.num)
        apiCall!!.enqueue(object : Callback<SeasonDetailsResponse> {
            override fun onResponse(call: Call<SeasonDetailsResponse>, response: Response<SeasonDetailsResponse>) {
                if (response.isSuccessful()) {

                    val items = response.body()!!
                    var episode : Episode

                    for (item in items.episodes!!){
                        episode = Episode(saison.serie?:"Aucun titre n'est disponible",saison.num, item.episode_number?:0, R.drawable.p1,R.drawable.p1, "")
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
                                PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(baseContext, saison.personnages)

                                personnesLieesRecycler_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

                                personnesLieesRecycler_view.adapter = PersonnesLieesAdapter
                            }


                        }
                    }

                    //
                    episodes_recycler_view.setHasFixedSize(true)
                    //
                    episodesAdapter = RecyclerViewEpisodesAdapter(baseContext, saison.episodes)

                    episodes_recycler_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

                    episodes_recycler_view.adapter = episodesAdapter

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<SeasonDetailsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadAssociatedPersons(serieId: Int) {
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
                        saison.personnages.add(p)
                    }

                    PersonnesLieesAdapter = RecyclerViewPersonnesAdapter(baseContext, saison.personnages)

                    personnesLieesRecycler_view.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)

                    personnesLieesRecycler_view.adapter = PersonnesLieesAdapter

                } else
                    loadFailed()
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                loadFailed()
            }
        })
    }

    private fun loadComments(serieId: Int) {
        apiCallComments = apiUser.getService().getComments(serieId)
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
