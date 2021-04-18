package com.example.thehinduclone.the_hindu.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thehinduclone.R
import com.example.thehinduclone.the_hindu.models.PersonaliseModel

class PersonaliseAdapter(private val selectionList: List<PersonaliseModel>,val itemClickListener: ItemClickListener) : RecyclerView.Adapter<PersonaliseViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaliseViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.personalize_item_layout, parent, false)
        return PersonaliseViewHolder(view,itemClickListener)
    }

    override fun getItemCount(): Int {
        return selectionList.size

    }

    override fun onBindViewHolder(holder: PersonaliseViewHolder, position: Int) {
        holder.setData(selectionList[position])

    }
}