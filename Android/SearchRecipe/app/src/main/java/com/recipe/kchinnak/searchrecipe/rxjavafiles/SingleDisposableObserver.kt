package com.recipe.kchinnak.searchrecipe.rxjavafiles

import android.util.Log
import com.recipe.kchinnak.searchrecipe.beanClasses.DetailRecipe
import com.recipe.kchinnak.searchrecipe.beanClasses.DetailRecipeKey
import io.reactivex.observers.DisposableObserver

class SingleDisposableObserver : DisposableObserver<DetailRecipeKey>() {

    private val TAG = SingleDisposableObserver::class.java.simpleName

    override fun onComplete() {
        Log.d(TAG, "The network call for detailed recipe completed.")
    }

    override fun onNext(t: DetailRecipeKey) {
        Log.d(TAG, "Network call is successful " + t.toString())
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "There was a network error while make the api call for detailed recipe. " + e.localizedMessage)
    }
}