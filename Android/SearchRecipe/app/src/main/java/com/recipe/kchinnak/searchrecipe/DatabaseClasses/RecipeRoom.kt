package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class RecipeRoom(rId: String, publisher: String, f2fUrl: String, title: String, sourceUrl: String, imageUrl: String, socialRank: Double, publisherUrl: String) {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "recipe_id")
    private lateinit var _recipe_id: String


    @ColumnInfo(name = "publisher")
    private var _publisher: String? = null


    @ColumnInfo(name = "f2fUrl")
    private var _f2fUrl: String? = null


    @ColumnInfo(name = "title")
    private var _title: String? = null


    @ColumnInfo(name = "sourceUrl")
    private var _sourceUrl: String? = null

    @ColumnInfo(name = "imageUrl")
    private var _imageUrl: String? = null

    @ColumnInfo(name = "socialRank")
    private var _socialRank: Double = 0.0

    @ColumnInfo(name = "publisherUrl")
    private var _publisherUrl: String? = null


    init {
        this._f2fUrl = f2fUrl
        this._imageUrl = imageUrl
        this._publisher = publisher
        this._publisherUrl = publisherUrl
        this._recipe_id = rId
        this._socialRank = socialRank
        this._title = title
        this._sourceUrl = sourceUrl
    }

    fun getRecipeId(): String{
        return _recipe_id
    }

}