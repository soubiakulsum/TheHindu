package com.thehindu.thehinduclone.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thehindu.R
import com.thehindu.themain.models.NewsResponse

class BriefingAdapter(private val selectionList: List<NewsResponse>) :
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