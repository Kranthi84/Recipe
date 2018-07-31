package com.recipe.kchinnak.searchrecipe


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.recipe.kchinnak.searchrecipe.fragments.HomeFragment


class MainActivity : AppCompatActivity(), MainFragment.SignedUser {

    override fun preSignedin() {
        var homeIntent: Intent = Intent(this, HomeActivity::class.java)
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
