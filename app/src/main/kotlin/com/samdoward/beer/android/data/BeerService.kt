package com.samdoward.beer.android.data

import com.samdoward.beer.android.data.Beer
import rx.Observable

interface BeerService {

    fun getBeers(): Observable<List<Beer>>

}
