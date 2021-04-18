package com.thehindu.themain

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.thehindu.R
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.remote.ApiClient
import com.thehindu.themain.remote.Network
import kotlinx.android.synthetic.main.activity_selected_news.*
import kotlinx.android.synthetic.main.rv_home_item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SelectedNewsActivity : AppCompatActivity() {
    val apiClient = Network.getInstance().create(ApiClient::class.java)
    var newsResponse: NewsResponse? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_news)
        PreferenceHelper.getSharedPreferences(this);
        getTheNews(intent.getIntExtra("newsid", 0))
        val x = PreferenceHelper.readIntFromPreference("fontSize");
        if (x != 0) {
            tvMainTitle.textSize = x.toFloat() + 2
            tvMainAuthor.textSize = x.toFloat() - 4
            tvMainDate.textSize = x.toFloat() - 4
            tvMainSubTitle.textSize = x.toFloat() - 4
            tvMainContent.textSize = x.toFloat()
        }
        ivBack.setOnClickListener {
            this.finish()
        }
    }

    private fun getTheNews(id: Int) {
        apiClient.getByNewsId(
            "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE),
            id
        ).enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                newsResponse = response.body()
                if (newsResponse != null) {
                    tvMainTitle.text = newsResponse?.headline
                    tvMainAuthor.text =
                        (newsResponse?.author + "  " + newsResponse?.location).toLowerCase()
                    tvMainSubTitle.text = newsResponse?.subHeading

                    tvMainContent.text =
                        LocalConstants.getGoodString(newsResponse?.content)
                    Glide.with(ivMainImage).load(newsResponse?.multiMedia).into(ivMainImage)
                    val formatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val b = LocalDateTime.parse(newsResponse?.postedAt, formatter)
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
                    topText.text = "$str"
                }
            }
        })
    }
}