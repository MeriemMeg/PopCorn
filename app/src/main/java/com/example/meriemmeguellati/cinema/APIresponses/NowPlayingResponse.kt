package com.example.meriemmeguellati.cinema.APIresponses

import com.google.gson.annotations.SerializedName

class NowPlayingResponse {
   /* @SerializedName("dates")
    private var dates: Dates? = null*/

    @SerializedName("page")
    private var page: Int = 0

    @SerializedName("total_pages")
    private var totalPages: Int = 0

    @SerializedName("results")
    private var results: List<MovieResponse>? = null

    @SerializedName("total_results")
    private var totalResults: Int = 0

   /* fun setDates(dates: Dates) {
        this.dates = dates
    }

    fun getDates(): Dates? {
        return dates
    } */

    fun setPage(page: Int) {
        this.page = page
    }

    fun getPage(): Int {
        return page
    }

    fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }

    fun getTotalPages(): Int {
        return totalPages
    }

    fun setResults(results: List<MovieResponse>) {
        this.results = results
    }

    fun getResults(): List<MovieResponse>? {
        return results
    }

    fun setTotalResults(totalResults: Int) {
        this.totalResults = totalResults
    }

    fun getTotalResults(): Int {
        return totalResults
    }

    override fun toString(): String {
        return "NowPlayingResponse{" +
                //"dates = '" + dates + '\''.toString() +
                ",page = '" + page + '\''.toString() +
                ",total_pages = '" + totalPages + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",total_results = '" + totalResults + '\''.toString() +
                "}"
    }
}