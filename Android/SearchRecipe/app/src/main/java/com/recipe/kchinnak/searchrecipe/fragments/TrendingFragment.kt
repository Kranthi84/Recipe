package com.recipe.kchinnak.searchrecipe.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom

import com.recipe.kchinnak.searchrecipe.R
import com.recipe.kchinnak.searchrecipe.RxJavaDisposableObserver
import com.recipe.kchinnak.searchrecipe.RxJavaPresenter

class TrendingFragment : Fragment(),RxJavaDisposableObserver.ViewModelInterface {
    override fun updatedRecipeList(mRecipeRoomList: ArrayList<RecipeRoom>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = TrendingFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.trending_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        RxJavaPresenter(this).getTrendingRecipes()
    }

}
