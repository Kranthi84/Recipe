package com.recipe.kchinnak.searchrecipe

import android.content.Context
import androidx.fragment.app.Fragment
import com.recipe.kchinnak.searchrecipe.BeanClasses.Recipe
import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.Interfaces.RecipeInterface
import com.recipe.kchinnak.searchrecipe.Interfaces.RetrofitInterface
import com.recipe.kchinnak.searchrecipe.Utils.ConfigUtil
import com.recipe.kchinnak.searchrecipe.Utils.NetworkUtil
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.math.MathContext


class RxJavaPresenter(mFragment: Fragment) : RecipeInterface {

    private var mFragment: Fragment = mFragment
    private var mRetrofit: Retrofit? = null



    override fun getRecipes() {
        getObservable().subscribeWith(getObserver())
    }

    private fun getObservable(): Observable<RecipesList> {
        var mNetworkUtil = NetworkUtil.instance
        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getRecipes(ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.api_key))) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserver(): DisposableObserver<RecipesList> {
        return RxJavaDisposableObserver(mFragment)
    }
}