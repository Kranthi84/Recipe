package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import com.recipe.kchinnak.searchrecipe.BeanClasses.Recipe
import io.reactivex.Flowable

class ImplementRecipeDataSource(mDaoAccess: DaoAccess) : RecipeDataSource {


    private val mDaoAccess: DaoAccess

    init {
        this.mDaoAccess = mDaoAccess
    }

    override fun getRecipe(recipe_id: Int): Flowable<RecipeRoom> {
        return mDaoAccess.fetchRecipeById(recipe_id)
    }

    override fun insertMultipleRecipes(listOfRecipes: List<RecipeRoom>) {

        mDaoAccess.insertMultipleRecipes(listOfRecipes)
    }

    override fun insertOrUpdateRecipe(recipe: RecipeRoom) {


        mDaoAccess.insertOneRecipe(recipe)
    }


    override fun deleteRecipe(recipe: RecipeRoom) {
        mDaoAccess.deleteRecipe(recipe)
    }

    override fun deleteAllRecipes() {
        mDaoAccess.deleteRecipes()
    }
}