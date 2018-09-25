package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.databaseClasses.TrendingRecipeRoom
import com.recipe.kchinnak.searchrecipe.R
import com.squareup.picasso.Picasso

class TrendingRecipeAdapter(recipes: List<TrendingRecipeRoom>, mContext: Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var queryString = p0.toString()
                if (queryString.isBlank()) {
                    mFilteredTrendingList = mRecipeList
                } else {
                    var filteredList = ArrayList<TrendingRecipeRoom>()

                    for (tRecipeRoom in mRecipeList) {

                        var tRoomTitle = tRecipeRoom._title

                        tRoomTitle.let {
                            if (it!!.contains(queryString, true)) filteredList.add(tRecipeRoom)
                        }
                    }

                    mFilteredTrendingList = filteredList
                }

                var filterResults = FilterResults()
                filterResults.values = mFilteredTrendingList
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                mFilteredTrendingList = p1?.values as ArrayList<TrendingRecipeRoom>
                notifyDataSetChanged()
            }

        }
    }


    private var context = mContext
    private var mRecipeList = recipes as ArrayList<TrendingRecipeRoom>
    private var mFilteredTrendingList = mRecipeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {

        var mView: View = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view_recipe, parent, false)
        return RecipeAdapter.RecipeViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mFilteredTrendingList.size
    }


    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.tvTitle.text = mFilteredTrendingList.get(position)._title
        Picasso.get().load(mFilteredTrendingList.get(position)._imageUrl).into(holder.recipeImageView)
    }


}