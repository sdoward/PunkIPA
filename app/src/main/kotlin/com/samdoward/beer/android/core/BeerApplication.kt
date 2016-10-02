package com.samdoward.beer.android.core

import android.app.Application
import com.facebook.stetho.Stetho

class BeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}
