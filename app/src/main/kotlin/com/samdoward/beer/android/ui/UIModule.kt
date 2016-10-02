package com.samdoward.beer.android.ui

import com.cruxapp.android.core.threading.AppThreadTransformer
import com.samdoward.beer.android.data.BeerService
import dagger.Module
import dagger.Provides

@Module
class UIModule(private val beerView: BeerView) {

    @Provides
    fun providesBeerView(beerService: BeerService): BeerPresenter {
        return BeerPresenter(beerService, beerView, AppThreadTransformer())
    }

}
