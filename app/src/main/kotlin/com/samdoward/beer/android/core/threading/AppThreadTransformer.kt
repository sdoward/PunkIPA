package com.cruxapp.android.core.threading

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AppThreadTransformer : ThreadTransfomer {

    override fun <T> applySchedulers(): Observable.Transformer<T, T> {
        return Observable.Transformer<T, T> { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
