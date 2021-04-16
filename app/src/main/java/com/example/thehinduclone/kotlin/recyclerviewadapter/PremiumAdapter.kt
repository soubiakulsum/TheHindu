package com.example.thehinduclone.kotlin.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thehinduclone.R
import com.example.thehinduclone.kotlin.model.PremiumModel

class PremiumAdapter(private val selectionList: List<PremiumModel>) :
        RecyclerView.Adapter<PremiumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.premium_layout, parent, false)
        return PremiumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return selectionList.size

    }

    override fun onBindViewHolder(holder: PremiumViewHolder, position: Int) {
        holder.setData(selectionList[position])

    }
}