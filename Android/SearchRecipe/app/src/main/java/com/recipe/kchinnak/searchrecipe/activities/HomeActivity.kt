package com.recipe.kchinnak.searchrecipe.activities

import android.app.SearchManager
import android.content.Context

import android.os.Bundle

import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.recipe.kchinnak.searchrecipe.R

import com.recipe.kchinnak.searchrecipe.adapters.HomePagerAdapter
import com.recipe.kchinnak.searchrecipe.adapters.RecipeAdapter
import com.recipe.kchinnak.searchrecipe.adapters.TrendingRecipeAdapter
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import com.recipe.kchinnak.searchrecipe.fragments.TrendingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TopratedFragment.TopRatedInterface, TrendingFragment.TrendingInterface {


    private var mTrAdapter: RecipeAdapter? = null
    private var mTrendAdapter: TrendingRecipeAdapter? = null

    override fun exposeTrendingAdapter(mTrendingAdapter: TrendingRecipeAdapter) {
        this.mTrendAdapter = mTrendingAdapter
    }

    override fun exposeAdapter(mTopRatedAdapter: RecipeAdapter) {
        this.mTrAdapter = mTopRatedAdapter
    }


    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
    }


    private lateinit var mSearchView: SearchView
    private lateinit var mSearchManager: SearchManager

    lateinit var mHomePagerAdapter: FragmentPagerAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)

        if (getSystemService(Context.SEARCH_SERVICE) is SearchManager) {
            mSearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        }



        mSearchView = menu!!.findItem(R.id.search_view).actionView as SearchView
        mSearchView.setSearchableInfo(mSearchManager.getSearchableInfo(componentName))
        mSearchView.maxWidth = Integer.MAX_VALUE

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                mTrendAdapter.let {
                    it?.filter?.filter(query)
                }

                mTrAdapter.let {
                    it?.filter?.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mTrendAdapter.let { it?.filter?.filter(newText) }

                mTrAdapter.let {
                    it?.filter?.filter(newText)
                }
                return false
            }

        })


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.search_view) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        mHomePagerAdapter = HomePagerAdapter(supportFragmentManager, this)
        viewPager.adapter = mHomePagerAdapter
        viewPager.addOnPageChangeListener(this)
        tabLayout.setupWithViewPager(viewPager)

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            moveTaskToBack(true)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
