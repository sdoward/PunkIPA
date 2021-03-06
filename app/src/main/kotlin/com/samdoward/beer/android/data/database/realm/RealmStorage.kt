package com.samdoward.beer.android.data.database.realm

import com.samdoward.beer.android.data.Beer
import com.samdoward.beer.android.data.database.Storage
import io.realm.Realm
import rx.Observable

class RealmStorage(private val realm: Realm) : Storage {

    override fun getBeers(): Observable<List<Beer>> {
        val realm = Realm.getDefaultInstance()
        return realm.where(BeerData::class.java).findAll()
                .asObservable()
                .map {
                    it.map {
                        Beer(it.id, it.name!!, it.tagLine!!, it.description!!, it.abv, it.foodPairing!!)
                    }
                }
    }

    override fun putBeer(beer: Beer) {
        val realm = Realm.getDefaultInstance()
        val beerData = BeerData()
        beerData.id = beer.id
        beerData.name = beer.name
        beerData.tagLine = beer.tagLine
        beerData.description = beer.description
        beerData.abv = beer.abv
        beerData.foodPairing = beer.foodPairing
        realm.beginTransaction();
        realm.copyToRealm(beerData)
        realm.commitTransaction();
    }
}
