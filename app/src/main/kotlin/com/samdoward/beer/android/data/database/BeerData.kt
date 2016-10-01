package com.samdoward.beer.android.data.database

import io.realm.RealmObject

open class BeerData() : RealmObject() {

    var id: Int = -1
    var name: String? = null
    var tagLine: String? = null
    var description: String? = null
    var abv: Double = -1.0
    var foodPairing: String? = null
}
