package com.samdoward.beer.android.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.samdoward.beer.android.R
import com.samdoward.beer.android.data.BeerServiceImp
import com.samdoward.beer.android.data.network.PunkApi
import com.samdoward.beer.android.data.database.realm.RealmStorage
import com.samdoward.beer.android.data.database.sql.BeerOpenDatabaseHelper
import com.samdoward.beer.android.data.database.sql.SqlStorage
import io.realm.Realm
import kotlinx.android.synthetic.main.beer_activity.*
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers.io
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

class BeerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beer_activity)
        setSupportActionBar(toolbar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val trustManager = object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun getAcceptedIssuers(): Array<out X509Certificate> {
                return emptyArray()
            }

        }
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())
        val sslSocketFactory = sslContext.getSocketFactory();

        val okHttp = OkHttpClient.Builder()
                .hostnameVerifier { s, sslSession -> true }
                .sslSocketFactory(sslSocketFactory)
                .addInterceptor {
                    val credentials = Credentials.basic("52f7cdcf4d7b4f5f9caee7d28e20c60f", "")
                    val request = it.request().newBuilder().addHeader("Authorization", credentials).build()
                    it.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://punkapi.com/")
                .client(okHttp)
                .build()

        val punkApi = retrofit.create(PunkApi::class.java)
        Realm.init(this)
        val realm = Realm.getDefaultInstance()
        val realmStorage = RealmStorage(realm)

        val database = BeerOpenDatabaseHelper(this).writableDatabase
        val sqlStorage = SqlStorage(database)

        BeerServiceImp(punkApi, sqlStorage)
                .getBeers()
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d("count", "count is: " + it.size)
                            recyclerView.adapter = BeerAdapter(it)
                        },
                        { it.printStackTrace() })

    }
}
