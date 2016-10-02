package com.samdoward.beer.android.data

import com.samdoward.beer.android.data.database.Storage
import com.samdoward.beer.android.data.network.PunkApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BeerServiceModule {

    @Singleton
    @Provides
    fun providesBeerService(punkApi: PunkApi, storage: Storage): BeerService {
        return BeerServiceImp(punkApi, storage)
    }

}
