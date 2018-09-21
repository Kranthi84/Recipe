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


class RxJavaPresenter(mFragment: Fragment, pIndex: Int) : RecipeInterface {
    override fun getTrendingRecipes() {
        getObservable('t', this.pageIndex).subscribeWith(getObserver())
    }
    override fun getTopRatedRecipes() {
        getObservable('r', this.pageIndex).subscribeWith(getObserver())
    }
    private var mFragment: Fragment = mFragment
    private var mRetrofit: Retrofit? = null
    private var pageIndex: Int = pIndex

    private fun getObservable(category: Char, pageIndex: Int): Observable<RecipesList> {
        var mNetworkUtil = NetworkUtil.instance
        var queryMap: HashMap<String, String> = HashMap()

        var api_key: String = mFragment.getString(R.string.key)
        var api_key_Value: String = ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.api_key))!!
        var sort_key: String = mFragment.getString(R.string.sort)
        var sort_value = category
        var page_key: String = mFragment.getString(R.string.page)
        var page_value: Int = pageIndex

        queryMap.put(api_key, api_key_Value)
        queryMap.put(sort_key, sort_value.toString())
        queryMap.put(page_key, page_value.toString())

        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getSortedRecipes(queryMap) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserver(): DisposableObserver<RecipesList> {
        return RxJavaDisposableObserver(mFragment)
    }
}