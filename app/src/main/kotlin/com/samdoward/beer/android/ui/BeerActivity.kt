package com.samdoward.beer.android.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.samdoward.beer.android.R
import com.samdoward.beer.android.core.DaggerApplicationComponent
import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.database.StorageModule
import com.samdoward.beer.android.data.network.APIModule
import kotlinx.android.synthetic.main.beer_activity.*

import javax.inject.Inject


class BeerActivity : AppCompatActivity(), BeerView {

    @Inject
    lateinit var beerPresenter: BeerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)
        DaggerApplicationComponent.builder()
                .aPIModule(APIModule())
                .storageModule(StorageModule(this))
                .uIModule(UIModule(this))
                .build()
                .inject(this)
        setSupportActionBar(toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        beerPresenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        beerPresenter.stop()
    }

    override fun displayBeers(beers: List<Beer>) {
        recyclerView.adapter = BeerAdapter(beers)
    }

}
