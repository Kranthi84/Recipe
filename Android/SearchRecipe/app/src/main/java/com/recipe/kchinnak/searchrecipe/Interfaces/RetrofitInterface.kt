package com.recipe.kchinnak.searchrecipe.Interfaces

import com.recipe.kchinnak.searchrecipe.BeanClasses.RecipesList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET
    fun getRecipes(@Query("key") key: String?): Observable<RecipesList>
}