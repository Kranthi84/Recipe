package com.recipe.kchinnak.searchrecipe

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.*
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import io.reactivex.observers.DisposableObserver

class RxJavaDisposableObserver(mFragment: Fragment) : DisposableObserver<RecipesList>() {

    private val TAG = RxJavaDisposableObserver::class.java.simpleName
    private var fragment: Fragment = mFragment
    private var mRecipeDataSource: ImplementRecipeDataSource? = null
    private var ratedRecipeInterface: ViewModelInterface = mFragment as TopratedFragment
    private var recipeRoomList: ArrayList<RecipeRoom>


    init {
        var mDatabase = RecipeDatbase.getInstance(fragment.context!!)
        mRecipeDataSource = ImplementRecipeDataSource(mDatabase!!.recipeDao())
        recipeRoomList = ArrayList()

    }

    override fun onComplete() {
        Log.d(TAG, "Observer task completed.")
    }

    override fun onNext(t: RecipesList) {
        Log.d(TAG, t.toString())

        for (rec in t.recipes!!) {
            var recipeRoom = RecipeRoom(rec.recipeId!!, rec.publisher!!, rec.f2fUrl!!, rec.title!!, rec.sourceUrl!!, rec.imageUrl!!, rec.socialRank!!, rec.publisherUrl!!)
            recipeRoomList.add(recipeRoom)
        }

        ratedRecipeInterface.updatedRecipeList(recipeRoomList)
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, e.localizedMessage)
    }

    interface ViewModelInterface {
        fun updatedRecipeList(mRecipeRoomList: ArrayList<RecipeRoom>)
    }
}