package com.recipe.kchinnak.searchrecipe

import androidx.lifecycle.ViewModel
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.ImplementRecipeDataSource
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeDataSource
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import io.reactivex.Completable
import io.reactivex.Flowable

class RecipeViewModel(recipeDatasource: ImplementRecipeDataSource) : ViewModel() {

    private var mRecipeDataSource: ImplementRecipeDataSource

    private var mRecipe: RecipeRoom? = null

    init {
        this.mRecipeDataSource = recipeDatasource
    }


    fun getRecipe(mRecipeId: Int): Flowable<RecipeRoom> {

        mRecipeDataSource.getRecipe(mRecipeId).map { mRecipeRoom ->
            this.mRecipe = mRecipeRoom
        }

        return mRecipeDataSource.getRecipe(mRecipeId)
    }



}