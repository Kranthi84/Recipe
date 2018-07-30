package com.recipe.kchinnak.searchrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.recipe.kchinnak.searchrecipe.MainFragment
import com.recipe.kchinnak.searchrecipe.fragments.SignupFragment

class MainActivity : AppCompatActivity() {

    companion object {
        var USERTAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }



}
