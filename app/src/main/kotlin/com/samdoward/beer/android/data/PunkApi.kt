package com.samdoward.beer.android.data

import retrofit2.http.GET
import rx.Observable

interface PunkApi {

    @GET("api/v1/beers")
    fun getBeers(): Observable<List<Beer>>

}
