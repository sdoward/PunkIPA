package com.samdoward.beer.android.data.database.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sdoward.beer.android.data.database.sql.BeerModel

class BeerOpenDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "beer"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(BeerModel.CREATE_TABLE)
    }

    override fun onUpgrade(database: SQLiteDatabase, p1: Int, p2: Int) {
    }
}
