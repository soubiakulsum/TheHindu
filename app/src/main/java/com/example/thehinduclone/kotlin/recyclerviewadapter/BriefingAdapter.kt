package com.example.thehinduclone.kotlin.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thehinduclone.R
import com.example.thehinduclone.kotlin.model.BriefingModel

class BriefingAdapter(private val selectionList: List<BriefingModel>) :
        RecyclerView.Adapter<BriefingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BriefingViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.briefing_layout, parent, false)
        return BriefingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return selectionList.size

    }

    override fun onBindViewHolder(holder: BriefingViewHolder, position: Int) {
        holder.setData(selectionList[position])

    }
}