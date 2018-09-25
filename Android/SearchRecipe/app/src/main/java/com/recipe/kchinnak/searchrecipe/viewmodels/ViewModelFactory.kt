package com.recipe.kchinnak.searchrecipe.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.recipe.kchinnak.searchrecipe.databaseClasses.RecipeDataSource

class ViewModelFactory(recipeDataSource: RecipeDataSource) : ViewModelProvider.Factory {

    private var mRecipeDataSource: RecipeDataSource = recipeDataSource

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(mRecipeDataSource) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}