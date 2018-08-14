package com.recipe.kchinnak.searchrecipe


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.ImplementRecipeDataSource
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeDataSource
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import io.reactivex.Completable
import io.reactivex.Flowable

class RecipeViewModel(recipeDatasource: RecipeDataSource) : ViewModel() {

    private var mRecipeDataSource: RecipeDataSource

    private var mRecipe: RecipeRoom? = null

    private var roomRecipeList: ArrayList<RecipeRoom>? = null

    var mRecipeLiveData: MutableLiveData<ArrayList<RecipeRoom>>

    init {
        this.mRecipeDataSource = recipeDatasource
        roomRecipeList = ArrayList()
        mRecipeLiveData = MutableLiveData()
    }


    fun getRecipe(mRecipeId: Int): Flowable<RecipeRoom> {

        return mRecipeDataSource.getRecipe(mRecipeId).map { mRecipeRoom ->
            this.mRecipe = mRecipeRoom
            return@map mRecipe
        }

        //return mRecipeDataSource.getRecipe(mRecipeId)
    }

    fun insertSingleRecipe(mRecipeRoom: RecipeRoom) {

        mRecipeDataSource.insertOrUpdateRecipe(mRecipeRoom)

    }

    fun insertMultipleRecipes(mRecipeList: List<RecipeRoom>): Completable {

        return Completable.fromAction {
            mRecipeDataSource.insertMultipleRecipes(mRecipeList)
        }
    }

    fun setUpRecipeRoomArray(mRecipeList: ArrayList<RecipeRoom>) {
        this.roomRecipeList = mRecipeList


    }


}