package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import io.reactivex.Flowable

class ImplementRecipeDataSource(mDaoAccess: DaoAccess) : RecipeDataSource {

    private val mDaoAccess: DaoAccess = mDaoAccess

    override fun getTrendingRecipeList(): Flowable<List<TrendingRecipeRoom>> {
        return mDaoAccess.fetchAllTrendingRecipes()
    }

    override fun insertMultipleTrendingRecipes(listOfTrendingRecipes: List<TrendingRecipeRoom>) {
        mDaoAccess.insertMultipleTrendingRecipes(listOfTrendingRecipes)
    }

    override fun deleteAllTrendingRecipes() {
        mDaoAccess.deleteTrendingRecipes()
    }



    override fun getRecipeList(): Flowable<List<RecipeRoom>> {
        return mDaoAccess.fetchAllRecipes()
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