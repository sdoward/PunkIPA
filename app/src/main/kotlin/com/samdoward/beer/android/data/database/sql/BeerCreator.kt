package com.samdoward.beer.android.data.database.sql

import com.sdoward.beer.android.data.database.sql.BeerModel

class BeerCreator : BeerModel.Creator<BeerModelData> {

    override fun create(_id: Long, name: String, tagline: String, description: String, abv: Double, food_pariing: String): BeerModelData {
        return BeerModelData(_id.toInt(), name, tagline, description, abv, food_pariing)
    }

}
