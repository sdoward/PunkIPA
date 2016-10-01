package com.samdoward.beer.android.data

import rx.Observable

class FakePunkApi : PunkApi {

    override fun getBeers(): Observable<List<Beer>> {
        val beer1 = Beer(0, "name1", "Tagline1", "description1", 4.5, "Meat")
        val beer2 = Beer(1, "name2", "Tagline2", "description2", 4.5, "Meat")
        val beer3 = Beer(2, "name3", "Tagline3", "description3", 4.5, "Meat")
        val beer4 = Beer(3, "name4", "Tagline4", "description4", 4.5, "Meat")
        val beer5 = Beer(4, "name5", "Tagline5", "description5", 4.5, "Meat")
        val beer6 = Beer(5, "name6", "Tagline6", "description6", 4.5, "Meat")
        return Observable.just(listOf(beer1, beer2, beer3, beer4, beer5, beer6))
    }

}
