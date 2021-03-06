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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.meriemmeguellati.cinema.Adapters.*
import com.example.meriemmeguellati.cinema.BuildConfig
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.NavDrawerHelper


class FicheEpisodeActivity : AppCompatActivity() {

    var isCommentsShown : Boolean = false
    lateinit var episode : Episode
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    lateinit var backdrop : ImageView
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_personne_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        getSupportActionBar()!!.title=""



        val intent = intent
         this.episode = intent.getSerializableExtra("Episode") as Episode

        val nomActeur = findViewById<TextView>(R.id.nomActeur)
        nomActeur.text = this.episode.serie
        val naissance = findViewById<TextView>(R.id.bearthday)
        naissance.text = "Episode "+this.episode.saison.toString() + " x "+ this.episode.num.toString()

        backdrop = findViewById(R.id.profileImage)
        Glide.with(baseContext)
                .load(BuildConfig.BASE_URL_IMG + "w780" + episode.still_path)
                .apply(RequestOptions()
                        .placeholder(R.drawable.defaultfiche)
                        .centerCrop()
                )
                .into(backdrop)


        val description = findViewById<TextView>(R.id.film_description)
        description.text = episode.description


        val icon = findViewById<ImageButton>(R.id.play_stop)
        icon.setImageResource(R.drawable.ic_ondemand_video_white_24dp)

        val my_recycler_view = findViewById<RecyclerView>(R.id.filmgraphie)
        my_recycler_view.setHasFixedSize(true)

        val adapter = RecyclerViewChainesAdapter(this, createChannels())

        my_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        my_recycler_view.adapter = adapter
        initNavigationDrawer()

        //évènements du Click
   /*    more.setOnClickListener {
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

    */

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
        menuInflater.inflate(R.menu.menu_personne, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_comment-> {
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

       /* val commenter: Button = mView?.findViewById<Button>(R.id.commenter)
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


    fun  createChannels() :ArrayList<Chaine>{
        val chaines = ArrayList<Chaine>()
        chaines.add(Chaine(R.drawable.abc,"12/05/2018","20:00"))
        chaines.add(Chaine(R.drawable.net,"30/04/2018","22:00"))
        chaines.add(Chaine(R.drawable.disney,"1/05/2018","16:40"))
        return chaines
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

       /* val evaluer : Button = mView?.findViewById<Button>(R.id.submit)
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


}
