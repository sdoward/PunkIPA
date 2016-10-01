package com.samdoward.beer.android.data.database

import com.samdoward.beer.android.data.Beer
import rx.Observable

interface Storage {

    fun getBeers(): Observable<List<Beer>>

    fun putBeer(beer: Beer)

}
