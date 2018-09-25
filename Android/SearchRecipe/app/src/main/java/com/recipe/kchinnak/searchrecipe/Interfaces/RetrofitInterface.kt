package com.recipe.kchinnak.searchrecipe.interfaces


import com.recipe.kchinnak.searchrecipe.beanClasses.DetailRecipeKey
import com.recipe.kchinnak.searchrecipe.beanClasses.RecipesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitInterface {


    @GET("api/search")
    fun getSortedRecipes(@QueryMap(encoded = true) map: Map<String, String>): Observable<RecipesList>

    @GET("api/get")
    fun getRecipe(@QueryMap(encoded = true) map: Map<String, String>): Observable<DetailRecipeKey>

}