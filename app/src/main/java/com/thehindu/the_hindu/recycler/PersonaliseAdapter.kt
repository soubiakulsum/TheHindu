package com.thehindu.the_hindu.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thehindu.R
import com.thehindu.the_hindu.model.PersonaliseModel

class PersonaliseAdapter(private val selectionList: List<PersonaliseModel>) :
    RecyclerView.Adapter<PersonaliseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaliseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.personalize_item_layout, parent, false)
        return PersonaliseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return selectionList.size

    }

    override fun onBindViewHolder(holder: PersonaliseViewHolder, position: Int) {
        holder.setData(selectionList[position])

    }
}