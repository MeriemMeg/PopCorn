package com.example.meriemmeguellati.cinema.Model

/**
 * Created by Meriem Meguellati on 18/04/2018.
 */
import android.content.res.Resources
import com.example.meriemmeguellati.cinema.R
import java.io.Serializable

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
data class Data (
        var res : Resources

): Serializable {

    var films : ArrayList<Film> = ArrayList<Film>()
    var series : ArrayList<Serie> = ArrayList<Serie>()
    var personnes : ArrayList<Personne> = ArrayList<Personne>()
    var salle : ArrayList<Salle> = ArrayList<Salle>()


    fun createData (){
        var covers: IntArray = intArrayOf(
                R.drawable.americanhustle,
                R.drawable.commedesbetes,
                R.drawable.deathwish,
                R.drawable.happydeathday,
                R.drawable.hungergames,
                R.drawable.jumanji,
                R.drawable.leroyaumedegahool,
                R.drawable.mother,
                R.drawable.orientexpress,
                R.drawable.peterrabbit,
                R.drawable.serena,
                R.drawable.superlapin,
                R.drawable.whitenight,
                R.drawable.wintersbone,
                R.drawable.xmen
        )

        var covers2: IntArray = intArrayOf(
                R.drawable.americanblufftrailer,
                R.drawable.commedesbetestrailer,
                R.drawable.deathwishtrailer,
                R.drawable.deathdaytrailer,
                R.drawable.hangergamestrailer,
                R.drawable.jumanjitrailer,
                R.drawable.royaumetrailer,
                R.drawable.mothertrailer,
                R.drawable.murdertrailer,
                R.drawable.peterrabbittrailer,
                R.drawable.serenatrailer,
                R.drawable.zootopiatrailer,
                R.drawable.housetrailer,
                R.drawable.wintertrailer,
                R.drawable.xmantrailer
        )

        var personnesFiche: IntArray = intArrayOf(
                R.drawable.p1,
                R.drawable.p2,
                R.drawable.personne3,
                R.drawable.p4,
                R.drawable.p5,
                R.drawable.p6,
                R.drawable.p7,
                R.drawable.p8,
                R.drawable.p9,
                R.drawable.p10,
                R.drawable.p11,
                R.drawable.p12,
                R.drawable.p13
        )

        var personnesFiche2: IntArray = intArrayOf(
                R.drawable.p111,
                R.drawable.p22,
                R.drawable.p33,
                R.drawable.p44,
                R.drawable.p55,
                R.drawable.p66,
                R.drawable.p77,
                R.drawable.p88,
                R.drawable.p99,
                R.drawable.p1010,
                R.drawable.p1111,
                R.drawable.p1212,
                R.drawable.p1313
        )


        //definir les films
        var film1  = Film(res.getStringArray(R.array.film_1)[0], covers[0],res.getStringArray(R.array.film_1)[1],"commedesbetes", covers2[0])
        var film2 = Film(res.getStringArray(R.array.film_2)[0], covers[1],res.getStringArray(R.array.film_2)[1],"commedesbetes",covers2[1])
        var film3 = Film(res.getStringArray(R.array.film_3)[0], covers[2],res.getStringArray(R.array.film_3)[1],"commedesbetes",covers2[2])
        var film4 = Film(res.getStringArray(R.array.film_4)[0], covers[3],res.getStringArray(R.array.film_4)[1],"commedesbetes",covers2[3])
        var film5 = Film(res.getStringArray(R.array.film_5)[0], covers[4],res.getStringArray(R.array.film_5)[1],"commedesbetes",covers2[4])
        var film6 = Film(res.getStringArray(R.array.film_6)[0], covers[5],res.getStringArray(R.array.film_6)[1],"commedesbetes",covers2[5])
        var film7 = Film(res.getStringArray(R.array.film_7)[0], covers[6],res.getStringArray(R.array.film_7)[1],"commedesbetes",covers2[6])
        var film8 = Film(res.getStringArray(R.array.film_8)[0], covers[7],res.getStringArray(R.array.film_8)[1],"commedesbetes",covers2[7])
        var film9 = Film(res.getStringArray(R.array.film_9)[0], covers[8],res.getStringArray(R.array.film_9)[1],"commedesbetes",covers2[8])
        var film10 = Film(res.getStringArray(R.array.film_10)[0], covers[9],res.getStringArray(R.array.film_10)[1],"commedesbetes",covers2[9])
        var film11 = Film(res.getStringArray(R.array.film_11)[0], covers[10],res.getStringArray(R.array.film_11)[1],"commedesbetes",covers2[10])
        var film12 = Film(res.getStringArray(R.array.film_12)[0], covers[11],res.getStringArray(R.array.film_12)[1],"commedesbetes",covers2[11])
        var film13 = Film(res.getStringArray(R.array.film_13)[0], covers[12],res.getStringArray(R.array.film_13)[1],"commedesbetes",covers2[12])
        var film14 = Film(res.getStringArray(R.array.film_14)[0], covers[13],res.getStringArray(R.array.film_14)[1],"commedesbetes",covers2[13])
        var film15 = Film(res.getStringArray(R.array.film_15)[0], covers[14],res.getStringArray(R.array.film_15)[1],"commedesbetes",covers2[14])

        //personnes
        var p1 = Personne(res.getStringArray(R.array.personne_1)[0],res.getStringArray(R.array.personne_1)[1],personnesFiche[0],personnesFiche2[0],res.getStringArray(R.array.personne_1)[2])
        var p2 = Personne(res.getStringArray(R.array.personne_2)[0],res.getStringArray(R.array.personne_2)[1],personnesFiche[1],personnesFiche2[1],res.getStringArray(R.array.personne_2)[2])
        var p3 = Personne(res.getStringArray(R.array.personne_3)[0],res.getStringArray(R.array.personne_3)[1],personnesFiche[2],personnesFiche2[2],res.getStringArray(R.array.personne_3)[2])
        var p4 = Personne(res.getStringArray(R.array.personne_4)[0],res.getStringArray(R.array.personne_4)[1],personnesFiche[3],personnesFiche2[3],res.getStringArray(R.array.personne_4)[2])
        var p5 = Personne(res.getStringArray(R.array.personne_5)[0],res.getStringArray(R.array.personne_5)[1],personnesFiche[4],personnesFiche2[4],res.getStringArray(R.array.personne_5)[2])
        var p6 = Personne(res.getStringArray(R.array.personne_6)[0],res.getStringArray(R.array.personne_6)[1],personnesFiche[5],personnesFiche2[5],res.getStringArray(R.array.personne_6)[2])
        var p7 = Personne(res.getStringArray(R.array.personne_7)[0],res.getStringArray(R.array.personne_7)[1],personnesFiche[6],personnesFiche2[6],res.getStringArray(R.array.personne_7)[2])
        var p8 = Personne(res.getStringArray(R.array.personne_8)[0],res.getStringArray(R.array.personne_8)[1],personnesFiche[7],personnesFiche2[7],res.getStringArray(R.array.personne_8)[2])
        var p9 = Personne(res.getStringArray(R.array.personne_9)[0],res.getStringArray(R.array.personne_9)[1],personnesFiche[8],personnesFiche2[8],res.getStringArray(R.array.personne_9)[2])
        var p10 = Personne(res.getStringArray(R.array.personne_10)[0],res.getStringArray(R.array.personne_10)[1],personnesFiche[9],personnesFiche2[9],res.getStringArray(R.array.personne_10)[2])
        var p11 = Personne(res.getStringArray(R.array.personne_11)[0],res.getStringArray(R.array.personne_11)[1],personnesFiche[10],personnesFiche2[10],res.getStringArray(R.array.personne_11)[2])
        var p12 = Personne(res.getStringArray(R.array.personne_12)[0],res.getStringArray(R.array.personne_12)[1],personnesFiche[11],personnesFiche2[11],res.getStringArray(R.array.personne_12)[2])
        var p13 = Personne(res.getStringArray(R.array.personne_13)[0],res.getStringArray(R.array.personne_13)[1],personnesFiche[12],personnesFiche2[12],res.getStringArray(R.array.personne_13)[2])


        //filmographie des personnes :
        val filmographie1 = java.util.ArrayList<Film>(8)
        filmographie1.add(film1);filmographie1.add(film5);filmographie1.add(film8);filmographie1.add(film11);filmographie1.add(film13);filmographie1.add(film14);filmographie1.add(film15)
        p1.definirfilmographie(filmographie1)

        val filmographie2 = java.util.ArrayList<Film>(5)
        filmographie2.add(film1);filmographie2.add(film15);filmographie2.add(film3);filmographie2.add(film4);filmographie2.add(film5)
        p2.definirfilmographie(filmographie2);

        val filmographie3 = java.util.ArrayList<Film>(5)
        filmographie3.add(film2);filmographie3.add(film10);filmographie3.add(film12);filmographie3.add(film7);filmographie3.add(film6)

        val filmographie4 = java.util.ArrayList<Film>(5)
        filmographie4.add(film15);filmographie4.add(film9);filmographie4.add(film6);filmographie4.add(film1);filmographie4.add(film13)

        p3.definirfilmographie(filmographie4)
        p4.definirfilmographie(filmographie4)
        p5.definirfilmographie(filmographie3)
        p6.definirfilmographie(filmographie3)
        p7.definirfilmographie(filmographie3)
        p8.definirfilmographie(filmographie3)
        p9.definirfilmographie(filmographie3)
        p10.definirfilmographie(filmographie1)
        p11.definirfilmographie(filmographie1)
        p12.definirfilmographie(filmographie1)
        p13.definirfilmographie(filmographie1)

        this.personnes.add(p1)
        this.personnes.add(p2)
        this.personnes.add(p3)
        this.personnes.add(p4)
        this.personnes.add(p5)
        this.personnes.add(p6)
        this.personnes.add(p7)
        this.personnes.add(p8)
        this.personnes.add(p9)
        this.personnes.add(p10)
        this.personnes.add(p11)
        this.personnes.add(p1)







        //définir les films  et personnes liés
        //FILM1 :American Bluff
        val lies1 = java.util.ArrayList<Film>(5)
        lies1.add(film13);lies1.add(film11) ;lies1.add(film9);lies1.add(film14);lies1.add(film8)
        film1.definirFilmLies(lies1)

        val per1 = java.util.ArrayList<Personne>(5)
        per1.add(p1);per1.add(p2);per1.add(p11);per1.add(p10)
        film1.definirPersonnes(per1)


        //FILM2 :Comme des bêtes
        val lies2 = java.util.ArrayList<Film>(5)
        lies2.add(film7);lies2.add(film10) ;lies2.add(film12);lies2.add(film6);lies2.add(film15);
        film2.definirFilmLies(lies2)

        val per2 = java.util.ArrayList<Personne>(5)
        per2.add(p8);per2.add(p9);per2.add(p5);per2.add(p6);per2.add(p7)
        film2.definirPersonnes(per2)

        //FILM3 :Death Wish
        val lies3 = java.util.ArrayList<Film>(5)
        lies3.add(film4);lies3.add(film8) ;lies3.add(film11);lies3.add(film13);lies3.add(film5);
        film3.definirFilmLies(lies3)
        film3.definirPersonnes(per1)

        //FILM4 :Happy Deathday
        val lies4 = java.util.ArrayList<Film>(5)
        lies4.add(film3);lies4.add(film8) ;lies4.add(film11);lies4.add(film13);lies4.add(film5);
        film4.definirFilmLies(lies4)
        film4.definirPersonnes(per1)

        //FILM5 :Hunger Games
        val lies5 = java.util.ArrayList<Film>(5)
        lies5.add(film6);lies5.add(film15) ;lies5.add(film1);lies5.add(film11);lies5.add(film7);
        film5.definirFilmLies(lies5)
        val per5 = java.util.ArrayList<Personne>(5)
        per5.add(p1);per5.add(p12);per5.add(p13)
        film5.definirPersonnes(per5)

        //FILM6 :Jumanji
        val lies6 = java.util.ArrayList<Film>(5)
        lies6.add(film5);lies6.add(film15) ;lies6.add(film1);lies6.add(film11);lies6.add(film7);
        film6.definirFilmLies(lies6)
        film6.definirPersonnes(per5)

        //FILM7 :Le Royaume de Ga'Hool
        val lies7 = java.util.ArrayList<Film>(5)
        lies7.add(film2);lies7.add(film10) ;lies7.add(film12);lies7.add(film15);lies7.add(film6);
        film7.definirFilmLies(lies7)
        film7.definirPersonnes(per2)

        //FILM8 :Mother
        val lies8 = java.util.ArrayList<Film>(5)
        lies8.add(film4);lies8.add(film3) ;lies8.add(film11);lies8.add(film13);lies8.add(film5);
        film8.definirFilmLies(lies8)
        film7.definirPersonnes(per1)


        //FILM9 :Le Royaume de Ga'Hool
        val lies9 = java.util.ArrayList<Film>(5)
        lies9.add(film13);lies9.add(film11) ;lies9.add(film1);lies9.add(film14);lies9.add(film8);
        film9.definirFilmLies(lies9)
        film9.definirPersonnes(per1)


        //FILM10 :Le Royaume de Ga'Hool
        val lies10 = java.util.ArrayList<Film>(5)
        lies10.add(film7);lies10.add(film2) ;lies10.add(film12);lies10.add(film15);lies10.add(film6);
        film10.definirFilmLies(lies10)
        film10.definirPersonnes(per2)

        //FILM11 :Le Royaume de Ga'Hool
        val lies11 = java.util.ArrayList<Film>(5)
        lies11.add(film13);lies11.add(film1) ;lies11.add(film9);lies11.add(film14);lies11.add(film8);
        film11.definirFilmLies(lies11)
        film11.definirPersonnes(per1)

        //FILM12 :Le Royaume de Ga'Hool
        val lies12 = java.util.ArrayList<Film>(5)
        lies12.add(film7);lies12.add(film10) ;lies12.add(film2);lies12.add(film15);lies12.add(film6);
        film12.definirFilmLies(lies12)
        film12.definirPersonnes(per2)

        //FILM13 :Le Royaume de Ga'Hool
        val lies13 = java.util.ArrayList<Film>(5)
        lies13.add(film11);lies13.add(film1) ;lies13.add(film9);lies13.add(film14);lies13.add(film8);
        film13.definirFilmLies(lies13)
        film13.definirPersonnes(per5)

        //FILM14 :Le Royaume de Ga'Hool
        val lies14 = java.util.ArrayList<Film>(5)
        lies14.add(film11);lies14.add(film1) ;lies14.add(film9);lies14.add(film13);lies14.add(film8);
        film14.definirFilmLies(lies14)
        film14.definirPersonnes(per1)

        //FILM15 :Le Royaume de Ga'Hool
        val lies15 = java.util.ArrayList<Film>(5)
        lies15.add(film5);lies15.add(film6) ;lies15.add(film1);lies15.add(film11);lies15.add(film7);
        film15.definirFilmLies(lies15)
        val per15 = java.util.ArrayList<Personne>(5)
        per15.add(p3);per15.add(p4);per15.add(p1);per15.add(p12)
        film15.definirPersonnes(per15)


        val films = java.util.ArrayList<Film>()
        this.films.add(film1)
        this.films.add(film2)
        this.films.add(film3)
        this.films.add(film4)
        this.films.add(film5)
        this.films.add(film6)
        this.films.add(film7)
        this.films.add(film8)
        this.films.add(film9)
        this.films.add(film10)
        this.films.add(film11)
        this.films.add(film12)
        this.films.add(film13)
        this.films.add(film14)
        this.films.add(film15)


        var cover: IntArray = intArrayOf(
                R.drawable.img10,
                R.drawable.img11,
                R.drawable.img12,
                R.drawable.img13,
                R.drawable.img14,
                R.drawable.img15,
                R.drawable.img16,
                R.drawable.img17,
                R.drawable.img10,
                R.drawable.img11,
                R.drawable.img12,
                R.drawable.img13,
                R.drawable.img14,
                R.drawable.img15,
                R.drawable.img16,
                R.drawable.img17
        )

        var cover2 : IntArray = intArrayOf(
                R.drawable.revenge,
                R.drawable.once,
                R.drawable.gooddoctor,
                R.drawable.vampire,
                R.drawable.casa,
                R.drawable.blacklist
        )


        //séries
        val s1 = Serie(res.getStringArray(R.array.serie_1)[0], cover[0],res.getStringArray(R.array.serie_1)[1],cover2[0])
        val s2 = Serie(res.getStringArray(R.array.serie_2)[0], cover[1],res.getStringArray(R.array.serie_2)[1],cover2[1])
        val s3 = Serie(res.getStringArray(R.array.serie_3)[0], cover[2],res.getStringArray(R.array.serie_3)[1],cover2[2])
        val s4 = Serie(res.getStringArray(R.array.serie_4)[0], cover[3],res.getStringArray(R.array.serie_4)[1],cover2[3])
        val s5 = Serie(res.getStringArray(R.array.serie_5)[0], cover[4],res.getStringArray(R.array.serie_5)[1],cover2[4])
        val s6 = Serie(res.getStringArray(R.array.serie_6)[0], cover[5],res.getStringArray(R.array.serie_6)[1],cover2[5])

        //saisons

        val s1s1 = Saison(res.getStringArray(R.array.serie_1)[0],1,cover[0],res.getStringArray(R.array.serie_1_saison1)[0],cover2[0],"gooddoctortrailer")
        val s1s2 = Saison(res.getStringArray(R.array.serie_1)[0],2,cover[0],res.getStringArray(R.array.serie_1_saison2)[0],cover2[0],"gooddoctortrailer")
        val s1s3 = Saison(res.getStringArray(R.array.serie_1)[0],3,cover[0],res.getStringArray(R.array.serie_1_saison3)[0],cover2[0],"gooddoctortrailer")
        val s1s4 = Saison(res.getStringArray(R.array.serie_1)[0],4,cover[0],res.getStringArray(R.array.serie_1_saison4)[0],cover2[0],"gooddoctortrailer")
        //personnes
        s1s1.personnages.addAll(per1)
        s1s2.personnages.addAll(per1)
        s1s3.personnages.addAll(per1)
        s1s4.personnages.addAll(per1)
        //episodes
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_1)[0],1,i,cover[0],cover2[0],"gooddoctortrailer")
            s1s1.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_1)[0],2,i,cover[0],cover2[0],"gooddoctortrailer")
            s1s2.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_1)[0],3,i,cover[0],cover2[0],"gooddoctortrailer")
            s1s3.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_1)[0],4,i,cover[0],cover2[0],"gooddoctortrailer")
            s1s4.episodes.add(eps6);
        }

        s1.saisons.add(s1s1)
        s1.saisons.add(s1s2)
        s1.saisons.add(s1s3)
        s1.saisons.add(s1s4)


        val s2s1 = Saison(res.getStringArray(R.array.serie_2)[0],1,cover[1],res.getStringArray(R.array.serie_2_saison1)[0],cover2[1],"gooddoctortrailer")
        val s2s2 = Saison(res.getStringArray(R.array.serie_2)[0],2,cover[1],res.getStringArray(R.array.serie_2_saison2)[0],cover2[1],"gooddoctortrailer")
        val s2s3 = Saison(res.getStringArray(R.array.serie_2)[0],3,cover[1],res.getStringArray(R.array.serie_2_saison3)[0],cover2[1],"gooddoctortrailer")
        val s2s4 = Saison(res.getStringArray(R.array.serie_2)[0],4,cover[1],res.getStringArray(R.array.serie_2_saison4)[0],cover2[1],"gooddoctortrailer")
        val s2s5 = Saison(res.getStringArray(R.array.serie_2)[0],5,cover[1],res.getStringArray(R.array.serie_2_saison5)[0],cover2[1],"gooddoctortrailer")
        val s2s6 = Saison(res.getStringArray(R.array.serie_2)[0],6,cover[1],res.getStringArray(R.array.serie_2_saison6)[0],cover2[1],"gooddoctortrailer")
       //personnes
        s2s1.personnages.addAll(per2)
        s2s2.personnages.addAll(per2)
        s2s3.personnages.addAll(per2)
        s2s4.personnages.addAll(per2)
        s2s5.personnages.addAll(per2)
        s2s6.personnages.addAll(per2)
        //episodes
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],1,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s1.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],2,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s2.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],3,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s3.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],4,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s4.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],5,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s5.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_2)[0],6,i,cover[1],cover2[1],"gooddoctortrailer")
            s2s6.episodes.add(eps6);
        }
        s2.saisons.add(s2s1)
        s2.saisons.add(s2s2)
        s2.saisons.add(s2s3)
        s2.saisons.add(s2s4)
        s2.saisons.add(s2s5)
        s2.saisons.add(s2s6)


        val s3s1 = Saison(res.getStringArray(R.array.serie_3)[0],1,cover[2],res.getStringArray(R.array.serie_3_saison1)[0],cover2[2],"gooddoctortrailer")
        //personnes
        s3s1.personnages.addAll(per5)
        //episodes
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_3)[0],1,i,cover[2],cover2[2],"gooddoctortrailer")
            s3s1.episodes.add(eps6);
        }
        s3.saisons.add(s3s1)

        val s4s1 = Saison(res.getStringArray(R.array.serie_4)[0],1,cover[3],res.getStringArray(R.array.serie_4_saison1)[0],cover2[3],"gooddoctortrailer")
        val s4s2 = Saison(res.getStringArray(R.array.serie_4)[0],2,cover[3],res.getStringArray(R.array.serie_4_saison2)[0],cover2[3],"gooddoctortrailer")
        val s4s3 = Saison(res.getStringArray(R.array.serie_4)[0],3,cover[3],res.getStringArray(R.array.serie_4_saison3)[0],cover2[3],"gooddoctortrailer")
        val s4s4 = Saison(res.getStringArray(R.array.serie_4)[0],4,cover[3],res.getStringArray(R.array.serie_4_saison4)[0],cover2[3],"gooddoctortrailer")
        val s4s5 = Saison(res.getStringArray(R.array.serie_4)[0],5,cover[3],res.getStringArray(R.array.serie_4_saison5)[0],cover2[3],"gooddoctortrailer")
        //personnes
        s4s1.personnages.addAll(per15)
        s4s2.personnages.addAll(per15)
        s4s3.personnages.addAll(per15)
        s4s4.personnages.addAll(per15)
        s4s5.personnages.addAll(per15)
        //episodes :
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_4)[0],1,i,cover[3],cover2[3],"gooddoctortrailer")
            s4s1.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_4)[0],2,i,cover[3],cover2[3],"gooddoctortrailer")
            s4s2.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_4)[0],3,i,cover[3],cover2[3],"gooddoctortrailer")
            s4s3.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_4)[0],4,i,cover[3],cover2[3],"gooddoctortrailer")
            s4s4.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_4)[0],5,i,cover[3],cover2[3],"gooddoctortrailer")
            s4s5.episodes.add(eps6);
        }


        s4.saisons.add(s4s1)
        s4.saisons.add(s4s2)
        s4.saisons.add(s4s3)
        s4.saisons.add(s4s4)
        s4.saisons.add(s4s5)

        val s5s1 = Saison(res.getStringArray(R.array.serie_5)[0],1,cover[4],res.getStringArray(R.array.serie_5_saison1)[0],cover2[4],"gooddoctortrailer")
        val s5s2 = Saison(res.getStringArray(R.array.serie_5)[0],2,cover[4],res.getStringArray(R.array.serie_5_saison2)[0],cover2[4],"gooddoctortrailer")
        //personnes
        s5s1.personnages.addAll(per2)
        s5s2.personnages.addAll(per2)
        //episodes
        for (i in 1..7){
            val eps6 = Episode(res.getStringArray(R.array.serie_5)[0],1,i,cover[4],cover2[4],"gooddoctortrailer")
            s5s1.episodes.add(eps6);
        }

        for (i in 1..7){
            val eps6 = Episode(res.getStringArray(R.array.serie_5)[0],2,i,cover[4],cover2[4],"gooddoctortrailer")
            s5s2.episodes.add(eps6);
        }
        s5.saisons.add(s5s1)
        s5.saisons.add(s5s2)




        val s6s1 = Saison(res.getStringArray(R.array.serie_6)[0],1,cover[5],res.getStringArray(R.array.serie_6_saison1)[0],cover2[5],"gooddoctortrailer")
        val s6s2 = Saison(res.getStringArray(R.array.serie_6)[0],2,cover[5],res.getStringArray(R.array.serie_6_saison2)[0],cover2[5],"gooddoctortrailer")
        val s6s3 = Saison(res.getStringArray(R.array.serie_6)[0],3,cover[5],res.getStringArray(R.array.serie_6_saison3)[0],cover2[5],"gooddoctortrailer")
        val s6s4 = Saison(res.getStringArray(R.array.serie_6)[0],4,cover[5],res.getStringArray(R.array.serie_6_saison4)[0],cover2[5],"gooddoctortrailer")
        val s6s5 = Saison(res.getStringArray(R.array.serie_6)[0],5,cover[5],res.getStringArray(R.array.serie_6_saison5)[0],cover2[5],"gooddoctortrailer")
        //persones
        s6s1.personnages.addAll(per2)
        s6s2.personnages.addAll(per2)
        s6s3.personnages.addAll(per2)
        s6s4.personnages.addAll(per2)
        s6s5.personnages.addAll(per2)

        //episodes
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_6)[0],1,i,cover[5],cover2[5],"gooddoctortrailer")
            s6s1.episodes.add(eps6);
        }

        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_6)[0],2,i,cover[5],cover2[5],"gooddoctortrailer")
            s6s2.episodes.add(eps6);
        }

        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_6)[0],3,i,cover[5],cover2[5],"gooddoctortrailer")
            s6s3.episodes.add(eps6);
        }
        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_6)[0],4,i,cover[5],cover2[5],"gooddoctortrailer")
            s6s4.episodes.add(eps6);
        }

        for (i in 1..22){
            val eps6 = Episode(res.getStringArray(R.array.serie_6)[0],5,i,cover[5],cover2[5],"gooddoctortrailer")
            s6s5.episodes.add(eps6);
        }

        s6.saisons.add(s6s1)
        s6.saisons.add(s6s2)
        s6.saisons.add(s6s3)
        s6.saisons.add(s6s4)
        s6.saisons.add(s6s5)



        //series liées
        val serieLiees1 = ArrayList<Serie> (6)
        serieLiees1.add(s2)
        serieLiees1.add(s3)
        serieLiees1.add(s4)
        serieLiees1.add(s5)
        serieLiees1.add(s6)
        s1.seriesLiees.addAll(serieLiees1)

        val serieLiees2 = ArrayList<Serie> (6)
        serieLiees1.add(s1)
        serieLiees1.add(s3)
        serieLiees1.add(s4)
        serieLiees1.add(s5)
        serieLiees1.add(s6)
        s2.seriesLiees.addAll(serieLiees2)

        val serieLiees3 = ArrayList<Serie> (6)
        serieLiees1.add(s2)
        serieLiees1.add(s1)
        serieLiees1.add(s4)
        serieLiees1.add(s5)
        serieLiees1.add(s6)
        s3.seriesLiees.addAll(serieLiees3)

        val serieLiees4 = ArrayList<Serie> (6)
        serieLiees1.add(s2)
        serieLiees1.add(s3)
        serieLiees1.add(s1)
        serieLiees1.add(s5)
        serieLiees1.add(s6)
        s4.seriesLiees.addAll(serieLiees4)

        val serieLiees5 = ArrayList<Serie> (6)
        serieLiees1.add(s2)
        serieLiees1.add(s3)
        serieLiees1.add(s4)
        serieLiees1.add(s1)
        serieLiees1.add(s6)
        s5.seriesLiees.addAll(serieLiees5)

        val serieLiees6 = ArrayList<Serie> (6)
        serieLiees1.add(s2)
        serieLiees1.add(s3)
        serieLiees1.add(s4)
        serieLiees1.add(s5)
        serieLiees1.add(s1)
        s6.seriesLiees.addAll(serieLiees6)





        this.series.add(s1)
        this.series.add(s2)
        this.series.add(s3)
        this.series.add(s4)
        this.series.add(s5)
        this.series.add(s6)
        this.series.add(s1)
        this.series.add(s2)
        this.series.add(s3)
        this.series.add(s4)
        this.series.add(s5)
        this.series.add(s6)
        this.series.add(s1)
        this.series.add(s2)
        this.series.add(s3)
        this.series.add(s4)
        this.series.add(s5)
        this.series.add(s6)

    }





}