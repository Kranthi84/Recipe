package com.recipe.kchinnak.searchrecipe.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.*
import com.recipe.kchinnak.searchrecipe.databaseClasses.Injection
import com.recipe.kchinnak.searchrecipe.databaseClasses.TrendingRecipeRoom
import com.recipe.kchinnak.searchrecipe.activities.HomeActivity
import com.recipe.kchinnak.searchrecipe.adapters.TrendingRecipeAdapter
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaDisposableObserver
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaPresenter
import com.recipe.kchinnak.searchrecipe.viewmodels.RecipeViewModel
import com.recipe.kchinnak.searchrecipe.viewmodels.ViewModelFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.trending_fragment.*
import java.util.*

class TrendingFragment : Fragment(), RxJavaDisposableObserver.ViewModelInterface {


    private var mDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mRecipeModel: RecipeViewModel
    private lateinit var mRecipeModelFactory: ViewModelFactory
    private var mRecipeAdapter: TrendingRecipeAdapter? = null
    /*private lateinit var mSearchManager: SearchManager
    private lateinit var mSearchView: SearchView*/
    private lateinit var mTrendingInterface: TrendingInterface
    private var mPage: Int? = null

    @SuppressLint("CheckResult")
    override fun updatedRecipeList(mRecipeTrendingList: ArrayList<Any>) {

        Observable.fromCallable {
            if (mPage == 1)
                mRecipeModel.deleteAllTrendingRecipes()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
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


    }

    companion object {
        fun newInstance() = TrendingFragment()
    }


    /*override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.home, menu)

        mSearchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = menu!!.findItem(R.id.search_view).actionView as SearchView
        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(activity!!.componentName))
        mSearchView.maxWidth = Integer.MAX_VALUE

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mRecipeAdapter.let { it?.filter?.filter(query) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mRecipeAdapter.let { it?.filter?.filter(newText) }
                return false
            }

        })
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.search_view) return true
        return super.onOptionsItemSelected(item)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRecipeModelFactory = Injection.getRecipeViewModelFactory(context!!)
        mRecipeModel = ViewModelProviders.of(this, mRecipeModelFactory).get(RecipeViewModel::class.java)
        mPage = (activity!!.application as RecipeApplication).trendingPageIndex

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
            mRecipeAdapter?.notifyDataSetChanged()
            mTrendingInterface.exposeTrendingAdapter(mRecipeAdapter!!)
        })

        trending_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    loadmore_text.visibility = View.VISIBLE
                } else {
                    if (loadmore_text.visibility == View.VISIBLE) loadmore_text.visibility = View.GONE
                }
            }
        })


        loadmore_text.setOnClickListener {
            (activity!!.findViewById<ProgressBar>(R.id.progressBar) as ProgressBar).visibility = View.VISIBLE
            mPage.let {
                mPage = it!! + 1
                RxJavaPresenter(this, mPage!!).getTrendingRecipes()
            }
        }


    }

    override fun onStart() {
        super.onStart()
        mPage.let {
            RxJavaPresenter(this, it!!).getTrendingRecipes()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HomeActivity) {
            mTrendingInterface = context
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        if (activity is HomeActivity) {
            mTrendingInterface = activity
        }

    }

    interface TrendingInterface {
        fun exposeTrendingAdapter(mTrendingAdapter: TrendingRecipeAdapter)
    }
}
