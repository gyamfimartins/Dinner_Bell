package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.Allergy
import kotlinx.android.synthetic.main.singlerow_allergy.view.*

class AllergyAdapter(var allergyList: ArrayList<Allergy>, val deleteMeal: (allergy: Allergy) -> Unit): RecyclerView.Adapter<AllergyAdapter.AllergyViewHolder>() {

    fun updateMealList(newMealList: List<Allergy>) {
        allergyList.clear()
        allergyList.addAll(newMealList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = AllergyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.singlerow_allergy, parent, false)
    )

    override fun getItemCount() = allergyList.size

    override fun onBindViewHolder(holder: AllergyViewHolder, position: Int) {
        holder.bind(allergyList[position])

        holder.itemView.ivdelete.setOnClickListener {
            deleteMeal(allergyList[position])
        }
    }

    class AllergyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvingredient = view.tvingredient

        fun bind(allergy: Allergy) {
            tvingredient.text = allergy.strIngredient
        }
    }
}