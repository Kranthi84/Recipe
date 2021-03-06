package com.recipe.kchinnak.searchrecipe.beanClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class RecipesList {

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("recipes")
    @Expose
    var recipes: List<Recipe>? = null


}