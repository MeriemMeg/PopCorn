package com.example.meriemmeguellati.cinema.Fragments

import android.os.Build
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meriemmeguellati.cinema.R

class SettingsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener{

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        var notif = findPreference("notifications")
        notif.setOnPreferenceChangeListener(this)

        var action = findPreference("action")
        action.setOnPreferenceChangeListener(this)

        var animation = findPreference("animation")
        animation.setOnPreferenceChangeListener(this)

        var drama = findPreference("drama")
        drama.setOnPreferenceChangeListener(this)

        var fiction = findPreference("sience_fiction")
        fiction.setOnPreferenceChangeListener(this)

        var adventure = findPreference("adventure")
        adventure.setOnPreferenceChangeListener(this)

        var comedy = findPreference("comedy")
        comedy.setOnPreferenceChangeListener(this)

        var history = findPreference("history")
        history.setOnPreferenceChangeListener(this)

        var horror = findPreference("horror")
        horror.setOnPreferenceChangeListener(this)

        var romance = findPreference("romance")
        romance.setOnPreferenceChangeListener(this)

        var war = findPreference("war")
        war.setOnPreferenceChangeListener(this)

        var crime = findPreference("crime")
        crime.setOnPreferenceChangeListener(this)

        var family = findPreference("family")
        family.setOnPreferenceChangeListener(this)

        val PARAMS_NAME = "PARAMS"
        val params = context.getSharedPreferences(PARAMS_NAME, 0)
        val editeur = params.edit()
        editeur.putInt("precedent", 0)
        editeur.commit()


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPreferenceChange(p0: Preference?, p1: Any?): Boolean {
        val PARAMS_NAME = "PARAMS"
        val params = context.getSharedPreferences(PARAMS_NAME,0)
        val key = p0!!.getKey()
        val isOn = p1 as Boolean

        if (key == "notifications") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("notifications", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("notifications", 0)
                editeur.commit()

            }

            return true
        }

        if (key == "action") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Action", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Action", 0)
                editeur.commit()

            }

            return true
        }

        if (key == "animation") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Animation", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Animation", 0)
                editeur.commit()

            }

            return true
        }

        if (key == "drama") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Drama", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Drama", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "sience_fiction") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Sience_fiction", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Sience_fiction", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "adventure") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Adventure", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Adventure", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "comedy") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Comedy", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Comedy", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "history") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("History", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("History", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "horror") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Horror", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Horror", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "romance") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Romance", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Romance", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "war") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("War", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("War", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "crime") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Crime", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Crime", 0)
                editeur.commit()

            }

            return true
        }
        if (key == "family") {
            if (isOn) {
                val editeur = params.edit()
                editeur.putInt("Family", 1)
                editeur.commit()



            } else {
                val editeur = params.edit()
                editeur.putInt("Family", 0)
                editeur.commit()

            }

            return true
        }


        return false
    }







}