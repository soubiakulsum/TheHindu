package com.thehindu.the_hindu.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thehindu.the_hindu.model.PersonaliseModel
import kotlinx.android.synthetic.main.personalize_item_layout.view.*

class PersonaliseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(personaliseModel: PersonaliseModel) {
        view.apply {
            tvSelectNews.text = personaliseModel.name
        }
    }

}