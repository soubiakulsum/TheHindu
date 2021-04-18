package com.thehindu.themain.models.recyclerview

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thehindu.R
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.thehindu.themain.LocalConstants
import com.thehindu.themain.PreferenceHelper
import com.thehindu.themain.SelectedNewsActivity
import com.thehindu.themain.listeners.CommunicationListernerForReadList
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.services.ClientCalls.Companion.getInstances
import kotlinx.android.synthetic.main.rv_home_item_layout.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsRvViewHolder(
    itemView: View,
    val communicationListernerForReadList: CommunicationListernerForReadList
) : RecyclerView.ViewHolder(itemView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(newsResponse: NewsResponse) {
        try {
            itemView.apply {
                Glide.with(rv_home_img.context).load(newsResponse.multiMedia).into(rv_home_img)
                rv_home_btn_save.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
                for (x in SideNavActivity.savedListList)
                    if (x.news_id == newsResponse.id) {
                        rv_home_btn_save.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                        break
                    }
                Log.d("TAG", "setData: " + newsResponse.postedAt)
                rv_home_tv_heading.text = newsResponse.headline
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
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
                            x = a.hour - b.hour
                            if (x == 0) {
                                x = a.minute - b.minute
                                str = "$x minutes ago"
                            } else str = "$x Hours ago"

                        } else {
                            str = "$x days ago"
                        }
                    } else {
                        str = "$x month's ago"
                    }
                } else {
                    str = "$x year's ago"
                }
                rv_home_tv_time_date.text = "$str"
            }

            LocalConstants.API_CLIENT.isNewsPresent(
                "Bearer " + PreferenceHelper.readStringFromPreference(
                    LocalConstants.PREF_TOKEN_VALUE
                ), newsResponse.id
            )
            Log.d("TAG", "setData: " + SideNavActivity.savedListList.size)
            itemView.rv_cv_home.setOnClickListener {
                val intent2 = Intent(itemView.context, SelectedNewsActivity::class.java)
                intent2.putExtra("newsid", newsResponse.id)
                itemView.context.startActivity(intent2)
            }

            itemView.rv_home_btn_save.setOnClickListener {
                val clientCalls = getInstances();
                clientCalls.isNewsExist(it, newsResponse.id)
                communicationListernerForReadList.save_it(newsResponse.id!!)
            }

            itemView.rv_home_btn_share.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, newsResponse.headline)
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://play.google.com/store/apps/details?id=" + it.context.getPackageName()
                )
                it.context.startActivity(Intent.createChooser(intent, "Share With"))
            }
        } catch (e: Exception) {

        }

    }
}