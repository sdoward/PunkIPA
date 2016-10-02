package com.samdoward.beer.android.ui

import com.cruxapp.android.core.threading.ThreadTransfomer
import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.BeerService
import rx.subscriptions.Subscriptions

class BeerPresenter(private val beerService: BeerService,
                    private val beerView: BeerView,
                    private val threadTransfomer: ThreadTransfomer) {

    private var subscription = Subscriptions.empty()

    fun start() {
        subscription = beerService.getBeers()
                .compose(threadTransfomer.applySchedulers<List<Beer>>())
                .subscribe({ beerView.displayBeers(it) }, { it.printStackTrace() })
    }

    fun stop() {
        subscription.unsubscribe()
    }

}

interface BeerView {

    fun displayBeers(beers: List<Beer>)

}
