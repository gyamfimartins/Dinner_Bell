package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.Category
import kotlinx.android.synthetic.main.singlerow_category.view.*

class CategoryAdapter(var categoryList: ArrayList<Category>,val clickListener: (imageTitle: String) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    fun updateCategoryList(newCategoryList: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CategoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.singlerow_category, parent, false)
    )

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
        holder.itemView.tvcategory.setOnClickListener {
            clickListener(categoryList[position].strCategory)
        }
    }

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvcategory = view.tvcategory

        fun bind(category: Category) {
            tvcategory.text = category.strCategory
        }
    }
}