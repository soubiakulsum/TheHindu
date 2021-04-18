package com.example.thehinduclone.the_hindu.recyclerView

import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.thehinduclone.R
import com.example.thehinduclone.the_hindu.models.PersonaliseModel
import kotlinx.android.synthetic.main.personalize_item_layout.view.*

class PersonaliseViewHolder(private val view: View, val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {

    fun setData(personaliseModel: PersonaliseModel) {
        view.apply {
            tvSelectNews.text = personaliseModel.name

            if (personaliseModel.isSelected) {
                cardPersonalise.setCardBackgroundColor(ContextCompat.getColor(tvSelectNews.context, R.color.blue))
                tvSelectNews.setTextColor(ContextCompat.getColor(cardPersonalise.context, R.color.white))
            } else {
                cardPersonalise.setCardBackgroundColor(ContextCompat.getColor(tvSelectNews.context, R.color.white))
                tvSelectNews.setTextColor(ContextCompat.getColor(cardPersonalise.context, R.color.blue))
            }

            cardPersonalise.setOnClickListener(View.OnClickListener {

                itemClickListener.onTemClicked(personaliseModel,adapterPosition) })

        }
    }

}