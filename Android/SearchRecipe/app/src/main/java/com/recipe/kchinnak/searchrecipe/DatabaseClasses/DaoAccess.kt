package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneRecipe(recipe: RecipeRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleRecipes(recipesList: List<RecipeRoom>)

    @Query("SELECT * FROM recipe WHERE recipe_id = :recipeId")
    fun fetchRecipeById(recipeId: Int): Flowable<RecipeRoom>

    @Query("SELECT * FROM recipe")
    fun fetchAllRecipes(): Flowable<List<RecipeRoom>>

    @Query("DELETE FROM recipe")
    fun deleteRecipes()

    @Update
    fun updateRecipe(recipe: RecipeRoom)

    @Delete
    fun deleteRecipe(recipe: RecipeRoom)

}