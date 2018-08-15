package com.recipe.kchinnak.searchrecipe.DatabaseClasses

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
class TrendingRecipeRoom(recipe_id: String, publisher: String, f2fUrl: String, title: String, sourceUrl: String, imageUrl: String, socialRank: Double, publisherUrl: String) {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "recipe_id")
    var _recipe_id: String


    @ColumnInfo(name = "publisher")
    var _publisher: String? = null


    @ColumnInfo(name = "f2fUrl")
    var _f2fUrl: String? = null


    @ColumnInfo(name = "title")
    var _title: String? = null


    @ColumnInfo(name = "sourceUrl")
    var _sourceUrl: String? = null

    @ColumnInfo(name = "imageUrl")
    var _imageUrl: String? = null

    @ColumnInfo(name = "socialRank")
    var _socialRank: Double = 0.0

    @ColumnInfo(name = "publisherUrl")
    var _publisherUrl: String? = null


    init {
        this._f2fUrl = f2fUrl
        this._imageUrl = imageUrl
        this._publisher = publisher
        this._publisherUrl = publisherUrl
        this._recipe_id = recipe_id
        this._socialRank = socialRank
        this._title = title
        this._sourceUrl = sourceUrl
    }

    fun getRecipeId(): String {
        return _recipe_id
    }
}