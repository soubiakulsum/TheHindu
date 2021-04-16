package com.thehindu.themain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.thehindu.R
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.remote.ApiClient
import com.thehindu.themain.remote.Network
import kotlinx.android.synthetic.main.activity_selected_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedNewsActivity : AppCompatActivity() {
    val apiClient = Network.getInstance().create(ApiClient::class.java)
    var newsResponse: NewsResponse? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_news)

        getTheNews(intent.getIntExtra("newsid", 0))

    }

    private fun getTheNews(id: Int) {
        apiClient.getByNewsId(
            "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE),
            id
        ).enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                newsResponse = response.body()
                if (newsResponse != null) {
                    tvMainTitle.text = newsResponse?.headline
                    tvMainAuthor.text = newsResponse?.author + "  " + newsResponse?.location
                    tvMainSubTitle.text = newsResponse?.subHeading
                    tvMainContent.text = newsResponse?.content
                    Glide.with(ivMainImage).load(newsResponse?.multiMedia).into(ivMainImage)
                }
            }
        })
    }
}