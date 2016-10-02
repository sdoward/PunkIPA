package com.samdoward.beer.android.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Module
class APIModule {

    @Provides
    @Singleton
    fun provideTrustManager(): X509TrustManager {
        return BlindFaithTrustManager()
    }

    @Provides
    @Singleton
    fun provideSSLSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(trustManager), SecureRandom())
        return sslContext.socketFactory;
    }

    @Provides
    @Singleton
    fun provideOKHttp(sslSocketFactory: SSLSocketFactory): OkHttpClient {
        return OkHttpClient.Builder()
                .hostnameVerifier { s, sslSession -> true }
                .sslSocketFactory(sslSocketFactory)
                .addNetworkInterceptor(StethoInterceptor())
                .authenticator { route, response ->
                    val credentials = Credentials.basic("52f7cdcf4d7b4f5f9caee7d28e20c60f", "")
                    response.request().newBuilder().header("Authorization", credentials).build()
                }
                .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://punkapi.com/")
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providePunkAPI(retrofit: Retrofit): PunkApi {
        return retrofit.create(PunkApi::class.java)
    }


}
