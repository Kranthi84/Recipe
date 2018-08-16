package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.TrendingRecipeRoom
import com.recipe.kchinnak.searchrecipe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recycler_view_recipe.view.*

class TrendingRecipeAdapter(recipes: List<TrendingRecipeRoom>, mContext: Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {



    private var context = mContext
    private var mRecipeList = recipes as ArrayList<TrendingRecipeRoom>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {

        var mView: View = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view_recipe, parent, false)
        return RecipeAdapter.RecipeViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mRecipeList.size
    }


    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.tvTitle.text = mRecipeList.get(position)._title
        Picasso.get().load(mRecipeList.get(position)._imageUrl).into(holder.recipeImageView)
    }




}