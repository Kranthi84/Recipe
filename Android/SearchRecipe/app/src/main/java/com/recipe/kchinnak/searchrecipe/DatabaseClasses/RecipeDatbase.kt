package com.recipe.kchinnak.searchrecipe.databaseClasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(RecipeRoom::class, TrendingRecipeRoom::class), version = 2)
abstract class RecipeDatbase : RoomDatabase() {

    abstract fun recipeDao(): DaoAccess


    companion object {
        private var INSTANCE: RecipeDatbase? = null

        fun getInstance(mContext: Context): RecipeDatbase? {

            if (INSTANCE == null) {

                synchronized(RecipeDatbase::class) {
                    INSTANCE = Room.databaseBuilder(mContext.applicationContext, RecipeDatbase::class.java, "recipe.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }

            return INSTANCE
        }


    }

    fun destroyDatabase() {
        INSTANCE = null
    }
}