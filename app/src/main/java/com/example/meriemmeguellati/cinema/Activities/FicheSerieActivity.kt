package com.example.meriemmeguellati.cinema.Activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.*
import com.example.meriemmeguellati.cinema.Adapters.CommentsFragment
import com.example.meriemmeguellati.cinema.Adapters.RecyclerViewSaisonAdapter
import com.example.meriemmeguellati.cinema.Data.Data
import com.example.meriemmeguellati.cinema.Model.*
import com.example.meriemmeguellati.cinema.R


class FicheSerieActivity : AppCompatActivity() {

    var isCommentsShown : Boolean = false
    lateinit var serie : Serie
    lateinit var data : Data
    lateinit var more : ImageButton
    lateinit var showComments : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fiche_serie_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        //getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
       // getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar()!!.title=""
        val my_recycler_view = findViewById<RecyclerView>(R.id.saisons)

        my_recycler_view.setHasFixedSize(true)

        val intent = intent
        this.serie = intent.getSerializableExtra("serie") as Serie
        val adapter = RecyclerViewSaisonAdapter(this, this.serie.saisons)

        my_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        my_recycler_view.adapter = adapter

        val name = findViewById<TextView>(R.id.serie_name)
        name.text = this.serie.titre

        val description = findViewById<TextView>(R.id.series_descrp)
        description.text = this.serie.description

        val affiche = findViewById<FrameLayout>(R.id.affiche)
        affiche.setBackgroundResource(this.serie.poster)

        this.data = Data(resources)
        this.data.createComments()
        this.showComments = findViewById<TextView>(R.id.nb_comments)
        this.showComments.text = "Commentaires (4)"
        this.more = findViewById<ImageButton>(R.id.more)

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


}
