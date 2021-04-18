package com.thehindu.thehinduclone.recyclerviewadapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.thehindu.R
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.thehindu.themain.SelectedNewsActivity
import com.thehindu.themain.models.NewsResponse
import kotlinx.android.synthetic.main.briefing_layout.view.*

class BriefingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val headings: TextView = view.findViewById<TextView>(R.id.tvHeading)
    val content: TextView = view.findViewById<TextView>(R.id.tvContent)
    val name: TextView = view.findViewById<TextView>(R.id.tvName)
    val time: TextView = view.findViewById<TextView>(R.id.tvTime)
    var image: ImageView = view.findViewById<ImageView>(R.id.ivImage)

    fun setData(briefingModel: NewsResponse) {

        headings.text = briefingModel.headline
        name.text = briefingModel.author
        time.text = briefingModel.postedAt
        content.text = briefingModel.content
        Glide.with(view)
            .load(briefingModel.multiMedia)
            .into(image)
        view.card.setOnClickListener {
            val intetn = Intent(view.context, SelectedNewsActivity::class.java)
            intetn.putExtra("newsid", briefingModel.id)
            view.context.startActivity(intetn)
        }
    }

}