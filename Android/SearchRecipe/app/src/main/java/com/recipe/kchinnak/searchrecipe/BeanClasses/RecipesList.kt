package com.recipe.kchinnak.searchrecipe.BeanClasses

import com.squareup.moshi.Json



class RecipesList {

    @Json(name = "count")
    private val count: Int? = null
    @Json(name = "recipes")
    private val recipes: List<Recipe>? = null
}