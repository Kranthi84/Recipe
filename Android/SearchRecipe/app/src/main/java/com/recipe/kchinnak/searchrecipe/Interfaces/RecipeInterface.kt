package com.recipe.kchinnak.searchrecipe.interfaces

interface RecipeInterface {


    fun getTrendingRecipes()
    fun getTopRatedRecipes()
    fun getDetailRecipe(recipeId: String)
}