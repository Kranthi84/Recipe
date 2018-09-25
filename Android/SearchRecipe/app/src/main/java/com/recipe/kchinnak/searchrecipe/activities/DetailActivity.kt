package com.recipe.kchinnak.searchrecipe.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.recipe.kchinnak.searchrecipe.R
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaPresenter

class DetailActivity : AppCompatActivity() {

    private var recipeId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        recipeId = intent.extras.getString(MainActivity.USERTAG)
        RxJavaPresenter(context = this).getDetailRecipe(recipeId!!)
    }

}
