package com.recipe.kchinnak.searchrecipe.Interfaces

import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitInterface {


    @GET("api/searchable")
    fun getSortedRecipes(@QueryMap(encoded = true) map: Map<String, String>): Observable<RecipesList>

}