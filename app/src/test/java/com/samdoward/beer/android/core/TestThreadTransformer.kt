package com.cruxapp.android.core

import com.cruxapp.android.core.threading.ThreadTransfomer
import rx.Observable
import rx.schedulers.Schedulers

class TestThreadTransformer : ThreadTransfomer {

    override fun <T> applySchedulers(): Observable.Transformer<T, T> {
        return Observable.Transformer<T, T> { observable ->
            observable.subscribeOn(Schedulers.immediate())
                    .observeOn(Schedulers.immediate())
        }
    }

}
