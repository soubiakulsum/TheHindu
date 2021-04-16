package com.example.thehinduclone.kotlin.recyclerviewadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thehinduclone.R
import com.example.thehinduclone.kotlin.model.BriefingModel
import com.example.thehinduclone.kotlin.model.PremiumModel

class BriefingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val headings: TextView =view.findViewById<TextView>(R.id.tvHeading)
    val content :TextView = view.findViewById<TextView>(R.id.tvContent)
    val name: TextView =view.findViewById<TextView>(R.id.tvName)
    val time: TextView =view.findViewById<TextView>(R.id.tvTime)
    var image: ImageView =view.findViewById<ImageView>(R.id.ivImage)

    fun setData(briefingModel: BriefingModel) {

        headings.text = briefingModel.headline
        name.text = briefingModel.name
        time.text = briefingModel.time
        content.text = briefingModel.content
        Glide.with(view)
                .load(view)
                .into(image)






    }

}