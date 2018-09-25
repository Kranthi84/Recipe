package com.recipe.kchinnak.searchrecipe.fragments


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.*
import com.recipe.kchinnak.searchrecipe.databaseClasses.Injection
import com.recipe.kchinnak.searchrecipe.databaseClasses.RecipeRoom
import com.recipe.kchinnak.searchrecipe.activities.HomeActivity
import com.recipe.kchinnak.searchrecipe.adapters.RecipeAdapter
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaDisposableObserver
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaPresenter
import com.recipe.kchinnak.searchrecipe.viewmodels.RecipeViewModel
import com.recipe.kchinnak.searchrecipe.viewmodels.ViewModelFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_toprated.*


class TopratedFragment : Fragment(), RxJavaDisposableObserver.ViewModelInterface {


    private val TAG: String = TopratedFragment::class.java.simpleName
    private var mDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var mRecipeViewModel: RecipeViewModel
    private lateinit var mViewModelFactory: ViewModelFactory
    private var mRecipeAdapter: RecipeAdapter? = null
   /* private lateinit var mSearchView: SearchView
    private lateinit var mSearchManager: SearchManager*/
    private var mPage: Int? = null
    private lateinit var mTopRInterface: TopRatedInterface


    @SuppressLint("CheckResult")
    override fun updatedRecipeList(mRecipeRoomList: ArrayList<Any>) {


        Observable.fromCallable {
            if (mPage == 1)
                mRecipeViewModel.deleteAllTopRatedRecipes()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    mDisposable.add(mRecipeViewModel.insertMultipleRecipes(mRecipeRoomList as ArrayList<RecipeRoom>).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe())

                    mDisposable.add(mRecipeViewModel.getAllRecipes().subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                mRecipeViewModel.mRecipeLiveData.value = it as ArrayList<RecipeRoom>
                            })

                }

    }


   /* override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
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

   /* override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.search_view) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModelFactory = Injection.getRecipeViewModelFactory(context!!)
        mRecipeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel::class.java)
        mPage = (activity!!.application as RecipeApplication).topRatedPageIndex
    }

    override fun onStart() {
        super.onStart()

        mPage.let {
            RxJavaPresenter(this, it!!).getTopRatedRecipes()
        }

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
            mRecipeAdapter!!.notifyDataSetChanged()
            mTopRInterface.exposeAdapter(mRecipeAdapter!!)
        })

        topRated_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    loadmore_text_toprated.visibility = View.VISIBLE
                } else {
                    if (loadmore_text_toprated.visibility == View.VISIBLE) loadmore_text_toprated.visibility = View.GONE
                }
            }
        })

        loadmore_text_toprated.setOnClickListener {
            (activity!!.findViewById<ProgressBar>(R.id.progressBar) as ProgressBar).visibility = View.VISIBLE
            mPage.let {
                mPage = it!! + 1
                RxJavaPresenter(this, mPage!!).getTopRatedRecipes()
            }
        }


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HomeActivity) {
            mTopRInterface = context as TopRatedInterface
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is HomeActivity) {
            mTopRInterface = activity as TopRatedInterface
        }
    }

    companion object {
        fun newInstance() = TopratedFragment()
    }

    interface TopRatedInterface {
        fun exposeAdapter(mTopRatedAdapter: RecipeAdapter)
    }
}
