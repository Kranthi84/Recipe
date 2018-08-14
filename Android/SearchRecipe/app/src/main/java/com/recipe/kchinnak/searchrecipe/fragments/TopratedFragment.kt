package com.recipe.kchinnak.searchrecipe.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.recipe.kchinnak.searchrecipe.*
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.Injection
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class TopratedFragment : Fragment(), RxJavaDisposableObserver.ViewModelInterface {

    private val TAG: String = TopratedFragment::class.java.simpleName

    private var mDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mRecipeViewModel: RecipeViewModel
    private lateinit var mViewModelFactory: ViewModelFactory

    override fun updatedRecipeList(mRecipeRoomList: ArrayList<RecipeRoom>) {
        mRecipeViewModel.setUpRecipeRoomArray(mRecipeRoomList)
        mDisposable.add(mRecipeViewModel.insertMultipleRecipes(mRecipeRoomList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())

        mDisposable.add(mRecipeViewModel.getRecipe(35382).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, it.getRecipeId())
                })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModelFactory = Injection.getRecipeViewModelFactory(context!!)
        mRecipeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel::class.java)


    }

    override fun onStart() {
        super.onStart()
        RxJavaPresenter(this).getRecipes()
    }

    override fun onStop() {
        super.onStop()
        mDisposable.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toprated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        fun newInstance() = TopratedFragment()
    }
}
