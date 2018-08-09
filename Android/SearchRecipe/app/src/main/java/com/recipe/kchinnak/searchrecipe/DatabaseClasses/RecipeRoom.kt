package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RecipeRoom {

    @NonNull
    @PrimaryKey
    private lateinit var _recipe_id: String


    private lateinit var _publisher: String


    private lateinit var _f2fUrl: String


    private lateinit var _title: String


    private lateinit var _sourceUrl: String


    private lateinit var _recipeId: String


    private lateinit var _imageUrl: String


    private var _socialRank: Double = 0.0


    private lateinit var _publisherUrl: String

}