package com.samdoward.beer.android.data

import com.samdoward.beer.android.data.BeerService
import com.samdoward.beer.android.data.database.Storage
import com.samdoward.beer.android.data.network.PunkApi
import rx.Observable

class BeerServiceImp(private val punkApi: PunkApi, private val storage: Storage) : BeerService {


    override fun getBeers(): Observable<List<Beer>> {
        return punkApi.getBeers()
                .doOnNext { it.forEach { storage.putBeer(it) } }
                .flatMap { storage.getBeers() }

    }

}
