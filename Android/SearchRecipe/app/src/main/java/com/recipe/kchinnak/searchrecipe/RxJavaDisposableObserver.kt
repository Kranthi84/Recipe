package com.recipe.kchinnak.searchrecipe


import android.util.Log
import androidx.fragment.app.Fragment

import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.*
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import com.recipe.kchinnak.searchrecipe.fragments.TrendingFragment
import io.reactivex.observers.DisposableObserver


class RxJavaDisposableObserver(mFragment: Fragment) : DisposableObserver<RecipesList>() {

    private val TAG = RxJavaDisposableObserver::class.java.simpleName
    private var fragment: Fragment = mFragment
    private var mRecipeDataSource: ImplementRecipeDataSource? = null
    private var recipeInterface: ViewModelInterface
    private var recipeList: ArrayList<Any>


    init {
        var mDatabase = RecipeDatbase.getInstance(fragment.context!!)
        mRecipeDataSource = ImplementRecipeDataSource(mDatabase!!.recipeDao())
        recipeList = ArrayList()
        recipeInterface = if (fragment is TopratedFragment) {
            fragment as TopratedFragment
        } else {
            fragment as TrendingFragment
        }

    }

    override fun onComplete() {
        Log.d(TAG, "Observer task completed.")
    }

    override fun onNext(t: RecipesList) {
        Log.d(TAG, t.toString())

        for (rec in t.recipes!!) {

            var recipeObject = if (fragment is TopratedFragment) {
                RecipeRoom(rec.recipeId!!, rec.publisher!!, rec.f2fUrl!!, rec.title!!, rec.sourceUrl!!, rec.imageUrl!!, rec.socialRank!!, rec.publisherUrl!!)
            } else {
                TrendingRecipeRoom(rec.recipeId!!, rec.publisher!!, rec.f2fUrl!!, rec.title!!, rec.sourceUrl!!, rec.imageUrl!!, rec.socialRank!!, rec.publisherUrl!!)
            }
            recipeList.add(recipeObject)
        }

        mRecipeDataSource?.apply {
            if (fragment is TopratedFragment)
                this?.deleteAllRecipes()
            else
                this?.deleteAllTrendingRecipes()
        }
        recipeInterface.updatedRecipeList(recipeList)
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, e.localizedMessage)
    }

    interface ViewModelInterface {
        fun updatedRecipeList(mRecipeList: ArrayList<Any>)
    }
}