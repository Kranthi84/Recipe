package com.recipe.kchinnak.searchrecipe.beanClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailRecipeKey {

    @SerializedName("recipe")
    @Expose
    var recipe: DetailRecipe? = null

}