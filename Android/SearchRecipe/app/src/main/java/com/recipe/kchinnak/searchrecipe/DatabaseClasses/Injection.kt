package com.recipe.kchinnak.searchrecipe.databaseClasses

import android.content.Context
import com.recipe.kchinnak.searchrecipe.viewmodels.ViewModelFactory

class Injection {

    companion object {
        fun getRecipeDataSource(mContext: Context): RecipeDataSource {
            val mDatbase = RecipeDatbase.getInstance(mContext)
            return ImplementRecipeDataSource(mDatbase?.recipeDao()!!)
        }

        fun getRecipeViewModelFactory(mContext: Context): ViewModelFactory {
            val mRecipeDataSource = getRecipeDataSource(mContext)
            return ViewModelFactory(mRecipeDataSource)
        }

    }
}