package com.thehindu.themain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.thehindu.R
import com.thehindu.the_hindu.activities.ProfileActivity
import com.thehindu.thehinduclone.recyclerviewadapter.BriefingAdapter
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.services.ClientCalls
import kotlinx.android.synthetic.main.activity_briefing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BriefingActivity : AppCompatActivity() {

    lateinit var newsList: List<NewsResponse>
    lateinit var briefing: BriefingAdapter
    val clientCalls = ClientCalls.getInstances()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_briefing)
        rvbriefing.layoutManager = LinearLayoutManager(this)

        breifingback.setOnClickListener {
            finish()
        }
        ivHome.setOnClickListener {
            startActivity(Intent(this, SideNavActivity::class.java))
        }
        ivMyAccount.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))

        }






        LocalConstants.API_CLIENT.getAllNews(
            "Bearer " + PreferenceHelper.readStringFromPreference(
                LocalConstants.PREF_TOKEN_VALUE
            )
        )
            .enqueue(object : Callback<List<NewsResponse>> {
                override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<NewsResponse>>,
                    response: Response<List<NewsResponse>>
                ) {
                    newsList = response.body()!!

                    briefing = BriefingAdapter(newsList)
                    rvbriefing.adapter = briefing
                    briefing.notifyDataSetChanged()
                }
            })
    }
}