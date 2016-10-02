package com.samdoward.beer.android.ui

import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.BeerService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BeerPresenter(private val beerService: BeerService, private val beerView: BeerView) {


    fun start() {
        beerService.getBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ beerView.displayBeers(it) }, { it.printStackTrace() })
    }

    fun stop() {

    }

}

interface BeerView {

    fun displayBeers(beers: List<Beer>)

}
