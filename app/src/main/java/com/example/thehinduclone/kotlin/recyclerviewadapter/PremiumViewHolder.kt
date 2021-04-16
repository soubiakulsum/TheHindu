package com.example.thehinduclone.kotlin.recyclerviewadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thehinduclone.R
import com.example.thehinduclone.kotlin.model.PremiumModel


class PremiumViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val headLine:TextView=view.findViewById<TextView>(R.id.tvHeadline)
    val name:TextView=view.findViewById<TextView>(R.id.tvName)
    val time:TextView=view.findViewById<TextView>(R.id.tvTime)
    var image: ImageView =view.findViewById<ImageView>(R.id.ivImage)

    fun setData(premiumModel: PremiumModel) {

        headLine.text = premiumModel.headline
        name.text = premiumModel.name
        time.text = premiumModel.time
        Glide.with(view)
                .load(view)
                .into(image)






    }

}