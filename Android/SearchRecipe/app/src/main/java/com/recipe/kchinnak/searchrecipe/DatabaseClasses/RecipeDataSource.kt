package com.recipe.kchinnak.searchrecipe.DatabaseClasses


import io.reactivex.Flowable

interface RecipeDataSource {

    fun getRecipe(recipe_id: Int): Flowable<RecipeRoom>

    fun insertOrUpdateRecipe(recipe: RecipeRoom)

    fun insertMultipleRecipes(listOfRecipes: List<RecipeRoom>)

    fun deleteRecipe(recipe: RecipeRoom)

    fun deleteAllRecipes()

}