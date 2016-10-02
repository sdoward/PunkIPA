package com.cruxapp.android.core.threading

import rx.Observable

interface ThreadTransfomer {

    fun <T> applySchedulers(): Observable.Transformer<T, T>

}
