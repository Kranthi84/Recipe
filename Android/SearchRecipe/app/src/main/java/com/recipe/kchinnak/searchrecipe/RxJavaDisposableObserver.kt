package com.recipe.kchinnak.searchrecipe

import android.util.Log
import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import io.reactivex.observers.DisposableObserver

class RxJavaDisposableObserver : DisposableObserver<RecipesList>() {

    private val TAG = RxJavaDisposableObserver::class.java.simpleName

    override fun onComplete() {
        Log.d(TAG, "Observer task completed.")
    }

    override fun onNext(t: RecipesList) {
        Log.d(TAG, t.toString())
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, e.localizedMessage)
    }
}