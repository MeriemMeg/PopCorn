package com.example.meriemmeguellati.cinema.Model

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class SectionDataModel(headerTitle: String, allItemsInSection: ArrayList<SingleItemModel>) {


    var headerTitle: String? = null
    var allItemsInSection: ArrayList<SingleItemModel>? = null


    init {

    }

    init {
        this.headerTitle = headerTitle
        this.allItemsInSection = allItemsInSection
    }


}