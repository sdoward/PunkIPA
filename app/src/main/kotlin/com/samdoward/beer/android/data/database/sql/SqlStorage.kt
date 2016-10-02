package com.samdoward.beer.android.data.database.sql

import android.database.sqlite.SQLiteDatabase
import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.database.Storage
import com.sdoward.beer.android.data.database.sql.BeerModel
import rx.Observable

class SqlStorage(private val database: SQLiteDatabase) : Storage {

    private val factory = BeerModel.Factory<BeerModelData>(BeerCreator())

    override fun getBeers(): Observable<List<Beer>> {
        return Observable.just(database.rawQuery(BeerModel.SELECT_ALL, emptyArray()))
                .map {
                    it.moveToFirst()
                    val beerList: MutableList<Beer> = mutableListOf()
                    while (!it.isLast) {
                        beerList.add(Beer(it.getLong(0).toInt(),
                                it.getString(1),
                                it.getString(2),
                                it.getString(3),
                                it.getDouble(4),
                                it.getString(5)))
                        it.moveToNext()
                    }
                    beerList
                }

    }

    override fun putBeer(beer: Beer) {
        val beerModel = BeerModelData(beer.id, beer.name, beer.tagLine, beer.description, beer.abv, beer.foodPairing)
        database.insert(BeerModel.TABLE_NAME, null, factory.marshal(beerModel).asContentValues())
    }
}
