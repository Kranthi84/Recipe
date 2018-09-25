package com.recipe.kchinnak.searchrecipe.databaseClasses


import io.reactivex.Flowable

interface RecipeDataSource {

    fun getRecipe(recipe_id: Int): Flowable<RecipeRoom>

    fun getRecipeList(): Flowable<List<RecipeRoom>>

    fun insertOrUpdateRecipe(recipe: RecipeRoom)

    fun insertMultipleRecipes(listOfRecipes: List<RecipeRoom>)

    fun deleteRecipe(recipe: RecipeRoom)

    fun deleteAllRecipes()

    fun getTrendingRecipeList(): Flowable<List<TrendingRecipeRoom>>

    fun insertMultipleTrendingRecipes(listOfTrendingRecipes: List<TrendingRecipeRoom>)

    fun deleteAllTrendingRecipes()

}