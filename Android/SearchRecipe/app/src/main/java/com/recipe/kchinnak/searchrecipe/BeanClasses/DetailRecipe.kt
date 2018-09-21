package com.recipe.kchinnak.searchrecipe.BeanClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DetailRecipe {

    @SerializedName("publisher")
    @Expose
    val publisher: String? = null

    @SerializedName("f2f_url")
    @Expose
    val f2fUrl: String? = null

    @SerializedName("ingredients")
    @Expose
    val ingredients: List<String>? = null

    @SerializedName("source_url")
    @Expose
    val sourceUrl: String? = null

    @SerializedName("recipe_id")
    @Expose
    val recipeId: String? = null

    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null

    @SerializedName("social_rank")
    @Expose
    val socialRank: Double? = null

    @SerializedName("publisher_url")
    @Expose
    val publisherUrl: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

}