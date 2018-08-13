package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.room.*
import com.recipe.kchinnak.searchrecipe.BeanClasses.Recipe
import io.reactivex.Flowable


interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneRecipe(recipe: RecipeRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleRecipes(recipesList: List<RecipeRoom>)

    @Query("SELECT * FROM recipe WHERE recipe_id = :recipeId")
    fun fetchRecipeById(recipeId: Int): Flowable<RecipeRoom>

    @Query("DELETE FROM recipe")
    fun deleteRecipes()

    @Update
    fun updateRecipe(recipe: RecipeRoom)

    @Delete
    fun deleteRecipe(recipe: RecipeRoom)

}