package com.recipe.kchinnak.searchrecipe

import android.content.Context
import androidx.fragment.app.Fragment
import com.recipe.kchinnak.searchrecipe.BeanClasses.DetailRecipe
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


class RxJavaPresenter : RecipeInterface {

    constructor(context: Context) {
        this.mContext = context
    }

    constructor(mFragment: Fragment, pIndex: Int) {
        this.mFragment = mFragment
        pageIndex = pIndex
    }

    override fun getTrendingRecipes() {
        getMultipleRecipeObservable('t', this.pageIndex!!).subscribeWith(getMultipleRecipeObserver())
    }

    override fun getTopRatedRecipes() {
        getMultipleRecipeObservable('r', this.pageIndex!!).subscribeWith(getMultipleRecipeObserver())
    }

    private lateinit var mContext: Context
    private lateinit var mFragment: Fragment
    private var pageIndex: Int? = null
    private var mRetrofit: Retrofit? = null
    private var mNetworkUtil = NetworkUtil.instance
    private var queryMap: HashMap<String, String> = HashMap()
    var api_key: String = mFragment.getString(R.string.key)
    var api_key_Value: String = ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.api_key))!!

    private fun getMultipleRecipeObservable(category: Char, pageIndex: Int): Observable<RecipesList> {

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

    private fun getSingleRecipeObservable(recipeId: String): Observable<DetailRecipe> {

        var recipeId_key = mContext.getString(R.string.recipe_id)

        queryMap.put(recipeId_key, recipeId)

        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getRecipe(queryMap) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    private fun getMultipleRecipeObserver(): DisposableObserver<RecipesList> {
        return RxJavaDisposableObserver(mFragment)
    }


}