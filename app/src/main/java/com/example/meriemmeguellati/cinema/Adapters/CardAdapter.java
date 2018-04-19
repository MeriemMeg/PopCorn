package com.example.meriemmeguellati.cinema.Adapters;

/**
 * Created by Meriem Meguellati on 31/03/2018.
 */

import android.support.v7.widget.CardView;

public interface CardAdapter {

    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
