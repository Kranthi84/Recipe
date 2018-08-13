package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class RecipeRoom {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "recipe_id")
    private lateinit var _recipe_id: String


    @ColumnInfo(name = "publisher")
    private lateinit var _publisher: String


    @ColumnInfo(name = "f2fUrl")
    private lateinit var _f2fUrl: String


    @ColumnInfo(name = "title")
    private lateinit var _title: String


    @ColumnInfo(name = "sourceUrl")
    private lateinit var _sourceUrl: String

    @ColumnInfo(name = "imageUrl")
    private lateinit var _imageUrl: String

    @ColumnInfo(name = "socialRank")
    private var _socialRank: Double = 0.0

    @ColumnInfo(name = "publisherUrl")
    private lateinit var _publisherUrl: String

}