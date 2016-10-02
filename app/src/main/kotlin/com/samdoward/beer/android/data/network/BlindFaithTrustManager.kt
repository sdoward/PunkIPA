package com.samdoward.beer.android.data.network

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class BlindFaithTrustManager : X509TrustManager {

    override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
    }

    override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
    }

    override fun getAcceptedIssuers(): Array<out X509Certificate> {
        return emptyArray()
    }

}
