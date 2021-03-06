package com.recipe.kchinnak.searchrecipe.rxjavafiles

import android.content.Context
import androidx.fragment.app.Fragment
import com.recipe.kchinnak.searchrecipe.beanClasses.DetailRecipe
import com.recipe.kchinnak.searchrecipe.beanClasses.RecipesList
import com.recipe.kchinnak.searchrecipe.interfaces.RecipeInterface
import com.recipe.kchinnak.searchrecipe.interfaces.RetrofitInterface
import com.recipe.kchinnak.searchrecipe.R
import com.recipe.kchinnak.searchrecipe.beanClasses.DetailRecipeKey
import com.recipe.kchinnak.searchrecipe.utils.ConfigUtil
import com.recipe.kchinnak.searchrecipe.utils.NetworkUtil
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

    override fun getDetailRecipe(recipeId: String) {
        getSingleRecipeObservable(recipeId).subscribeWith(getSingleRecipeObserver())
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


    private fun getMultipleRecipeObservable(category: Char, pageIndex: Int): Observable<RecipesList> {

        var sort_key: String = mFragment.getString(R.string.sort)
        var sort_value = category
        var page_key: String = mFragment.getString(R.string.page)
        var page_value: Int = pageIndex
        var api_key: String = mFragment.getString(R.string.key)
        var api_key_Value: String = ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.api_key))!!

        queryMap.put(api_key, api_key_Value)
        queryMap.put(sort_key, sort_value.toString())
        queryMap.put(page_key, page_value.toString())

        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mFragment.context!!, mFragment.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getSortedRecipes(queryMap) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getSingleRecipeObservable(recipeId: String): Observable<DetailRecipeKey> {

        var recipeId_key = mContext.getString(R.string.recipe_id)
        var api_key: String = mContext.getString(R.string.key)
        var api_key_Value: String = ConfigUtil().getConfigValue(mContext, mContext.getString(R.string.api_key))!!


        queryMap.put(api_key, api_key_Value)
        queryMap.put(recipeId_key, recipeId)

        mRetrofit = mNetworkUtil.retrofitBuilder(ConfigUtil().getConfigValue(mContext, mContext.getString(R.string.base_url)))
        return mRetrofit.let { mRetrofit?.create(RetrofitInterface::class.java)?.getRecipe(queryMap) }!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    private fun getMultipleRecipeObserver(): DisposableObserver<RecipesList> {
        return RxJavaDisposableObserver(mFragment)
    }

    private fun getSingleRecipeObserver(): DisposableObserver<DetailRecipeKey> {
        return SingleDisposableObserver()
    }

}