package com.samdoward.beer.android.ui

import com.cruxapp.android.core.TestThreadTransformer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.samdoward.beer.android.data.BeerService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import rx.Observable

class BeerPresenterTest {

    val beerService: BeerService = mock()
    val beerView: BeerView = mock()
    val threadTransfomer = TestThreadTransformer()
    val beerPresenter = BeerPresenter(beerService, beerView, threadTransfomer)

    @Before
    fun setUp() {
        Mockito.`when`(beerService.getBeers()).thenReturn(Observable.just(emptyList()))
    }

    @Test
    fun shouldGetBeers() {
        beerPresenter.start()
        verify(beerService).getBeers()
        verify(beerView).displayBeers(emptyList())
    }

}
