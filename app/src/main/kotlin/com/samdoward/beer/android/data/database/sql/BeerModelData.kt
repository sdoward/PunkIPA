package com.samdoward.beer.android.data.database.sql

import com.sdoward.beer.android.data.database.sql.BeerModel

class BeerModelData(val id: Int, val name: String, val tagLine: String?, val description: String, val abv: Double,
                    val foodPairing: String?) : BeerModel {

    override fun _id(): Long {
        return id.toLong()
    }

    override fun name(): String {
        return name
    }

    override fun tagline(): String {
        if (tagLine == null) {
            return ""
        } else {
            return tagLine
        }
    }

    override fun description(): String {
        return description
    }

    override fun abv(): Double {
        return abv
    }

    override fun food_paring(): String {
        if (foodPairing == null) {
            return ""
        } else {
            return foodPairing
        }
    }
}
