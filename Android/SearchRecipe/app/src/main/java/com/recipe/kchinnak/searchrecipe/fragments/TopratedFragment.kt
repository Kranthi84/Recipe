package com.recipe.kchinnak.searchrecipe.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.*
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.Injection
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import com.recipe.kchinnak.searchrecipe.adapters.RecipeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_toprated.*


class TopratedFragment : Fragment(), RxJavaDisposableObserver.ViewModelInterface {

    private val TAG: String = TopratedFragment::class.java.simpleName
    private var mDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mRecipeViewModel: RecipeViewModel
    private lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mRecipeAdapter: RecipeAdapter


    override fun updatedRecipeList(mRecipeRoomList: ArrayList<RecipeRoom>) {

        mDisposable.add(mRecipeViewModel.insertMultipleRecipes(mRecipeRoomList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())

        mDisposable.add(mRecipeViewModel.getAllRecipes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mRecipeViewModel.mRecipeLiveData.value = it as ArrayList<RecipeRoom>
                })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModelFactory = Injection.getRecipeViewModelFactory(context!!)
        mRecipeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel::class.java)


    }

    override fun onStart() {
        super.onStart()
        RxJavaPresenter(this).getTopRatedRecipes()
    }

    override fun onStop() {
        super.onStop()
        mDisposable.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_toprated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRated_recycler_view.layoutManager = LinearLayoutManager(context)
        mRecipeViewModel.mRecipeLiveData.observe(this, Observer {
            mRecipeAdapter = RecipeAdapter(it, context!!)
            topRated_recycler_view.adapter = mRecipeAdapter
            mRecipeAdapter.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance() = TopratedFragment()
    }
}
