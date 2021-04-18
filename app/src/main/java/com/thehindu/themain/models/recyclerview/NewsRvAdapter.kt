package com.thehindu.themain.models.recyclerview

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.thehindu.R
import com.thehindu.themain.listeners.CommunicationListernerForReadList
import com.thehindu.themain.models.NewsResponse

class NewsRvAdapter(
    val newsResponseList: List<NewsResponse>,
    val communicationListernerForReadList: CommunicationListernerForReadList
) :
    RecyclerView.Adapter<NewsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRvViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_home_item_layout, parent, false)
        return NewsRvViewHolder(v, communicationListernerForReadList)
    }

    override fun getItemCount(): Int {
        return newsResponseList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsRvViewHolder, position: Int) {
        try {
            holder.setData(newsResponseList[position])
        } catch (e: Exception) {

        }
    }
}