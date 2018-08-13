package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import android.content.Context
import androidx.lifecycle.ViewModelProvider

class Injection {

    companion object {
        fun getRecipeDataSource(mContext: Context): RecipeDataSource {
            val mDatbase = RecipeDatbase.getInstance(mContext)
            return ImplementRecipeDataSource(mDatbase?.recipeDao()!!)
        }


    }
}