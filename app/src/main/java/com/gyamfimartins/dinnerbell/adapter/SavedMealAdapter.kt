package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.SavedMeal
import com.retrofitcoroutines.example.utils.loadImage
import kotlinx.android.synthetic.main.singlerow_meallist.view.*

class SavedMealAdapter(var mealList: ArrayList<SavedMeal>, val clickListener: (mealId: String) -> Unit, val deleteMeal: (meal: SavedMeal) -> Unit): RecyclerView.Adapter<SavedMealAdapter.MealViewHolder>() {

    fun updateMealList(newMealList: List<SavedMeal>) {
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
        holder.itemView.btndelete.setOnClickListener {
            deleteMeal(mealList[position])
        }
    }

    class MealViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvstrMeal = view.tvstrMeal
        private val ivstrMealThumbid = view.ivstrMealThumbid
        private val btnDelete = view.btndelete

        fun bind(savedMeal: SavedMeal) {
            tvstrMeal.text = savedMeal.strMeal
            ivstrMealThumbid.loadImage(savedMeal.strMealThumb)
            btnDelete.visibility = View.VISIBLE
        }
    }
}