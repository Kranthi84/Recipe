package com.recipe.kchinnak.searchrecipe.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.recipe.kchinnak.searchrecipe.*
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.Injection
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.TrendingRecipeRoom
import com.recipe.kchinnak.searchrecipe.adapters.RecipeAdapter
import com.recipe.kchinnak.searchrecipe.adapters.TrendingRecipeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.trending_fragment.*

class TrendingFragment : Fragment(), RxJavaDisposableObserver.ViewModelInterface {

    private var mDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mRecipeModel: RecipeViewModel
    private lateinit var mRecipeModelFactory: ViewModelFactory
    private lateinit var mRecipeAdapter: TrendingRecipeAdapter

    override fun updatedRecipeList(mRecipeTrendingList: ArrayList<Any>) {
        mDisposable.add(mRecipeModel.insertMultipleTrendingRecipes(mRecipeTrendingList as ArrayList<TrendingRecipeRoom>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())

        mDisposable.add(mRecipeModel.getAllTrendingRecipes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mRecipeModel.mTrendingRecipeLiveData.value = it as ArrayList<TrendingRecipeRoom>
                })

    }

    companion object {
        fun newInstance() = TrendingFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecipeModelFactory = Injection.getRecipeViewModelFactory(context!!)
        mRecipeModel = ViewModelProviders.of(this, mRecipeModelFactory).get(RecipeViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.trending_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trending_recycler_view.layoutManager = LinearLayoutManager(context)
        mRecipeModel.mTrendingRecipeLiveData.observe(this, Observer {

            mRecipeAdapter = TrendingRecipeAdapter(it, context!!)
            trending_recycler_view.adapter = mRecipeAdapter
            mRecipeAdapter.notifyDataSetChanged()
            
        })
    }

    override fun onStart() {
        super.onStart()
        RxJavaPresenter(this).getTrendingRecipes()
    }

}
