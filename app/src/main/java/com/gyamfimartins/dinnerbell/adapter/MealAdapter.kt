package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.Meal
import com.retrofitcoroutines.example.utils.loadImage
import kotlinx.android.synthetic.main.singlerow_meallist.view.*

class MealAdapter(var mealList: ArrayList<Meal>, val clickListener: (imageTitle: String) -> Unit): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    fun updateMealList(newMealList: List<Meal>) {
        mealList.clear()
        mealList.addAll(newMealList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MealViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.singlerow_meallist, parent, false)
    )

    override fun getItemCount() = mealList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
        holder.itemView.card_view.setOnClickListener {
            clickListener(mealList[position].idMeal)
        }
    }

    class MealViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvstrMeal = view.tvstrMeal
        private val ivstrMealThumbid = view.ivstrMealThumbid

        fun bind(meal: Meal) {
            tvstrMeal.text = meal.strMeal
            ivstrMealThumbid.loadImage(meal.strMealThumb)
        }
    }
}