package com.recipe.kchinnak.searchrecipe


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.recipe.kchinnak.searchrecipe.BeanClasses.DetailRecipe

import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.*
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import com.recipe.kchinnak.searchrecipe.fragments.TrendingFragment
import io.reactivex.observers.DisposableObserver


class RxJavaDisposableObserver() : DisposableObserver<RecipesList>() {

    private val TAG = RxJavaDisposableObserver::class.java.simpleName
    private lateinit var fragment: Fragment
    private var mRecipeDataSource: ImplementRecipeDataSource? = null
    private var recipeInterface: ViewModelInterface
    private var recipeList: ArrayList<Any>


    constructor(mFragment: Fragment) : this() {
        this.fragment = mFragment
    }


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
        fragment.activity!!.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
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

        recipeInterface.updatedRecipeList(recipeList)
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, e.localizedMessage)
    }

    interface ViewModelInterface {
        fun updatedRecipeList(mRecipeList: ArrayList<Any>)
    }
}