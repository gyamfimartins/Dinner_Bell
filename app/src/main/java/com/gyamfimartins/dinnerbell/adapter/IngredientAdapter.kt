package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.Ingredient
import kotlinx.android.synthetic.main.singlerow_ingredient.view.*

class IngredientAdapter(var ingredientList: ArrayList<Ingredient>, val clickListener: (description: String) -> Unit): RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    fun updateIngredientList(newIngredientList: List<Ingredient>) {
        ingredientList.clear()
        ingredientList.addAll(newIngredientList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = IngredientViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.singlerow_ingredient, parent, false)
    )

    override fun getItemCount() = ingredientList.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredientList[position])
        holder.itemView.tvingredient.setOnClickListener {
            clickListener(ingredientList[position].strDescription?:"")
        }
    }

    class IngredientViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvingredient = view.tvingredient

        fun bind(ingredient: Ingredient) {
            tvingredient.text = ingredient.strIngredient

        }
    }
}