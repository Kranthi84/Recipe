package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.recipe.kchinnak.searchrecipe.BeanClasses.Recipe
import io.reactivex.Flowable


interface DaoAccess {

    @Insert
    fun insertOneRecipe(recipe: Recipe)

    @Insert
    fun insertMultipleRecipes(recipesList: List<Recipe>)

    @Query("SELECT * FROM RecipeRoom WHERE _recipe_id = :recipeId")
    fun fetchRecipeById(recipeId: Int): Flowable<Recipe>

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

}