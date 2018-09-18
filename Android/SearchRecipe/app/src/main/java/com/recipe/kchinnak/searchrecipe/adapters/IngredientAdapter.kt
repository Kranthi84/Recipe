package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recipe.kchinnak.searchrecipe.R
import kotlinx.android.synthetic.main.ingredients_layout.view.*

class IngredientAdapter(ingredients: ArrayList<String>, context: Context) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    private val mIngredients = ingredients
    private val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val mView = LayoutInflater.from(mContext).inflate(R.layout.ingredients_layout, parent, false)
        return IngredientViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mIngredients.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.mIngredientTextView.text = mIngredients.get(position)
    }

    class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mIngredientTextView = view.findViewById<TextView>(R.id.ingredient_text_View)
    }
}