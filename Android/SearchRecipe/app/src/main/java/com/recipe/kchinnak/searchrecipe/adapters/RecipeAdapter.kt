package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import com.recipe.kchinnak.searchrecipe.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recycler_view_recipe.view.*

class RecipeAdapter(recipes: List<RecipeRoom>, mContext: Context) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    private var context = mContext
    private var mRecipeList = recipes as ArrayList<RecipeRoom>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        var mView: View = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view_recipe, parent, false)
        return RecipeViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mRecipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.tvTitle.text = mRecipeList.get(position)._title
        Picasso.get().load(mRecipeList.get(position)._imageUrl).into(holder.recipeImageView)
    }


    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mView = view
        val tvTitle = mView.textview_recipe_title
        val recipeImageView = mView.imageView_recipe
    }


}