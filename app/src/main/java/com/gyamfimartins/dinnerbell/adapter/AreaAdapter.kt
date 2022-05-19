package com.gyamfimartins.dinnerbell.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.data.Area
import kotlinx.android.synthetic.main.singlerow_area.view.*

class AreaAdapter(var areaList: ArrayList<Area>): RecyclerView.Adapter<AreaAdapter.AreaViewHolder>() {

    fun updateAreaList(newareaList: List<Area>) {
        areaList.clear()
        areaList.addAll(newareaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = AreaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.singlerow_area, parent, false)
    )

    override fun getItemCount() = areaList.size

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bind(areaList[position])
    }

    class AreaViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tvarea = view.tvarea

        fun bind(area: Area) {
            tvarea.text = area.strArea

        }
    }
}