package com.samdoward.beer.android.ui

import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.BeerService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

class BeerPresenter(private val beerService: BeerService, private val beerView: BeerView) {

    private var subscription = Subscriptions.empty()

    fun start() {
        subscription = beerService.getBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ beerView.displayBeers(it) }, { it.printStackTrace() })
    }

    fun stop() {
        subscription.unsubscribe()
    }

}

interface BeerView {

    fun displayBeers(beers: List<Beer>)

}
