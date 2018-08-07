package com.recipe.kchinnak.searchrecipe.BeanClasses

import com.squareup.moshi.Json


class Recipe {

    @Json(name = "publisher")
    private val publisher: String? = null
    @Json(name = "f2f_url")
    private val f2fUrl: String? = null
    @Json(name = "title")
    private val title: String? = null
    @Json(name = "source_url")
    private val sourceUrl: String? = null
    @Json(name = "recipe_id")
    private val recipeId: String? = null
    @Json(name = "image_url")
    private val imageUrl: String? = null
    @Json(name = "social_rank")
    private val socialRank: Double? = null
    @Json(name = "publisher_url")
    private val publisherUrl: String? = null
}