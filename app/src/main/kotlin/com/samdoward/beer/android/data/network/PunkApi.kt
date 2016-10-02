package com.samdoward.beer.android.data.network

import retrofit2.http.GET
import rx.Observable

interface PunkApi {

    @GET("api/v1/beers")
    fun getBeers(): Observable<List<Beer>>

}
