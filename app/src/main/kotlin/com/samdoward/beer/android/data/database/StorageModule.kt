package com.samdoward.beer.android.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.samdoward.beer.android.data.database.sql.BeerOpenDatabaseHelper
import com.samdoward.beer.android.data.database.sql.SqlStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesDatabase(): SQLiteDatabase {
        return BeerOpenDatabaseHelper(context).writableDatabase
    }

    @Provides
    @Singleton
    fun providesStorage(database: SQLiteDatabase): Storage {
        return SqlStorage(database)
    }
}
