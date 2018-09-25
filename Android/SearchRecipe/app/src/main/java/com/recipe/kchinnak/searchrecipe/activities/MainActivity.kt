package com.recipe.kchinnak.searchrecipe.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.recipe.kchinnak.searchrecipe.fragments.MainFragment
import com.recipe.kchinnak.searchrecipe.R


class MainActivity : AppCompatActivity(), MainFragment.SignedUser {


    override fun preSignedin() {
        var homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }

    companion object {
        var USERTAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }

}
