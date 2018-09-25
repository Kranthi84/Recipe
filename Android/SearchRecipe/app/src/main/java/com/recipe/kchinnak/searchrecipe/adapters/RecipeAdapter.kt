package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.databaseClasses.RecipeRoom
import com.recipe.kchinnak.searchrecipe.activities.DetailActivity
import com.recipe.kchinnak.searchrecipe.activities.MainActivity
import com.recipe.kchinnak.searchrecipe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recycler_view_recipe.view.*

class RecipeAdapter(recipes: List<RecipeRoom>, mContext: Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var queryString = p0.toString()
                if (queryString.isBlank()) {
                    mFilteredList = mRecipeList
                } else {
                    var filteredList: ArrayList<RecipeRoom> = ArrayList<RecipeRoom>()
                    for (rRoom in mRecipeList) {
                        var rRoomTitle = rRoom._title
                        rRoomTitle.let {
                            if (it!!.contains(queryString, true)) filteredList.add(rRoom)
                        }
                    }

                    mFilteredList = filteredList
                }

                var mFilterResults: FilterResults = FilterResults()
                mFilterResults.values = mFilteredList
                return mFilterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                mFilteredList = p1?.values as ArrayList<RecipeRoom>
                notifyDataSetChanged()
            }

        }
    }

    private var context = mContext
    private var mRecipeList = recipes as ArrayList<RecipeRoom>
    private var mFilteredList = mRecipeList
    private var mIntent = Intent(context, DetailActivity::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        var mView: View = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view_recipe, parent, false)
        return RecipeViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.tvTitle.text = mFilteredList.get(position)._title
        Picasso.get().load(mFilteredList.get(position)._imageUrl).into(holder.recipeImageView)

        var recipeRoom = mFilteredList.get(position)

        mIntent.putExtra(MainActivity.USERTAG, recipeRoom.getRecipeId())

        holder.recipeImageView.setOnClickListener { context.startActivity(mIntent) }
        holder.tvTitle.setOnClickListener { context.startActivity(mIntent) }
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mView = view
        val tvTitle = mView.textview_recipe_title
        val recipeImageView = mView.imageView_recipe
    }
}