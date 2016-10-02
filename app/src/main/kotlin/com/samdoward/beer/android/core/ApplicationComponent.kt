package com.samdoward.beer.android.core

import com.samdoward.beer.android.data.BeerServiceModule
import com.samdoward.beer.android.data.database.StorageModule
import com.samdoward.beer.android.data.network.APIModule
import com.samdoward.beer.android.ui.BeerActivity
import com.samdoward.beer.android.ui.UIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(APIModule::class, StorageModule::class, BeerServiceModule::class, UIModule::class))
interface ApplicationComponent {

    fun inject(beerActivity: BeerActivity)

}
