package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.recipe.kchinnak.searchrecipe.ViewModelFactory

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