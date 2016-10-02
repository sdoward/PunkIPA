package com.samdoward.beer.android.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.samdoward.beer.android.R
import com.samdoward.beer.android.core.DaggerApplicationComponent
import com.samdoward.beer.android.data.BeerService
import com.samdoward.beer.android.data.database.StorageModule
import com.samdoward.beer.android.data.network.APIModule
import kotlinx.android.synthetic.main.beer_activity.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers.io

import javax.inject.Inject


class BeerActivity : AppCompatActivity() {

    @Inject
    lateinit var beerService: BeerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)
        DaggerApplicationComponent.builder()
                .aPIModule(APIModule())
                .storageModule(StorageModule(this))
                .build()
                .inject(this)
        setSupportActionBar(toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        beerService.getBeers()
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("count", "count is: " + it.size)
                            recyclerView.adapter = BeerAdapter(it)
                        },
                        { it.printStackTrace() })

    }
}
