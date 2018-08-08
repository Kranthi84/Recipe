package com.recipe.kchinnak.searchrecipe

import android.content.Context
import com.recipe.kchinnak.searchrecipe.BeanClasses.Recipe
import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.Interfaces.RecipeInterface
import com.recipe.kchinnak.searchrecipe.Interfaces.RetrofitInterface
import com.recipe.kchinnak.searchrecipe.Utils.ConfigUtil
import com.recipe.kchinnak.searchrecipe.Utils.NetworkUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.math.MathContext


class RxJavaPresenter(mContext: Context) : RecipeInterface {

    private var mContext: Context
    private var mRetrofit: Retrofit? = null


    init {
        this.mContext = mContext
    }

    override fun getRecipes() {
        getObservable().subscribeWith(getObserver())
    }

   private fun getObservable(): Observable<RecipesList> {
        var mNetworkUtil = NetworkUtil.instance
        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mContext, mContext.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getRecipes(ConfigUtil().getConfigValue(mContext, mContext.getString(R.string.api_key))) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

   private fun getObserver(): DisposableObserver<RecipesList> {
        return RxJavaDisposableObserver()
    }
}