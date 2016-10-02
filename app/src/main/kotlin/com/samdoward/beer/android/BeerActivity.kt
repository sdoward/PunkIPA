package com.samdoward.beer.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.samdoward.beer.android.data.BeerServiceImp
import com.samdoward.beer.android.data.FakePunkApi
import com.samdoward.beer.android.data.PunkApi
import com.samdoward.beer.android.data.database.realm.RealmStorage
import com.samdoward.beer.android.data.database.sql.BeerOpenDatabaseHelper
import com.samdoward.beer.android.data.database.sql.SqlStorage
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.beer_activity.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.schedulers.Schedulers.io

class BeerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)
        setSupportActionBar(toolbar)
        val okHttp = OkHttpClient.Builder()
                .addInterceptor {
                    val bytes = Base64.encode("52f7cdcf4d7b4f5f9caee7d28e20c60f".toByteArray(), Base64.NO_WRAP)
                    val request = it.request().newBuilder().addHeader("Authorization", "Basic ".plus(bytes)).build()
                    it.proceed(request)
                }.build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://punkapi.com/")
                .client(okHttp)
                .build()

        val punkApi = retrofit.create(PunkApi::class.java)
        Realm.init(this)
        val realm = Realm.getDefaultInstance()

        val database = BeerOpenDatabaseHelper(this).writableDatabase

        BeerServiceImp(FakePunkApi(), SqlStorage(database))
                .getBeers()
                .subscribeOn(io())
                .subscribe(
                        { Log.d("something", it.size.toString()) },
                        { it.printStackTrace() })

    }
}
