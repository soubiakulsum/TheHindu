package com.thehindu.themain.models.recyclerview

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thehindu.themain.LocalConstants
import com.thehindu.themain.PreferenceHelper
import com.thehindu.themain.SelectedNewsActivity
import com.thehindu.themain.listeners.CommunicationListernerForReadList
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.services.ClientCalls
import kotlinx.android.synthetic.main.rv_home_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsRvViewHolder(
    itemView: View,
    val communicationListernerForReadList: CommunicationListernerForReadList
) : RecyclerView.ViewHolder(itemView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(newsResponse: NewsResponse) {
        itemView.apply {
            Glide.with(rv_home_img.context).load(newsResponse.multiMedia).into(rv_home_img)
            rv_home_tv_heading.text = newsResponse.headline
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val b = LocalDateTime.parse(newsResponse.postedAt, formatter)
            val a = LocalDateTime.now()
            Log.d(
                "TAG", "setData: $b"
            )

            var str = ""
            var x = a.year - b.year
            if (x == 0) {
                x = a.month.value - b.month.value
                if (x == 0) {
                    x = a.dayOfMonth - b.dayOfMonth
                    if (x == 0) {
                        str = "today"
                    } else {
                        str = "$x days ago"
                    }
                } else {
                    str = "$x month's ago"
                }
            } else {
                str = "$x year's ago"
            }
            rv_home_tv_time_date.text = "posted $str"
        }

        itemView.rv_cv_home.setOnClickListener {
            val intent2 = Intent(itemView.context, SelectedNewsActivity::class.java)
            intent2.putExtra("newsid", newsResponse.id)
            itemView.context.startActivity(intent2)
        }



        itemView.rv_home_btn_save.setOnClickListener {
            val clientCalls = ClientCalls.getInstances();
            clientCalls.isNewsExist(it,newsResponse.id)
        }

        itemView.rv_home_btn_share.setOnClickListener {

        }

    }
}