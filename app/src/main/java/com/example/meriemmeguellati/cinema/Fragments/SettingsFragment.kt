package com.example.meriemmeguellati.cinema.Fragments

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meriemmeguellati.cinema.R

class SettingsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        var action = findPreference("action")
        action.setOnPreferenceChangeListener(this)

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



    }

    override fun onPreferenceChange(p0: Preference?, p1: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPreferenceClick(p0: Preference?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }





}