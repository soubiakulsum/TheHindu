package com.thehindu.themain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.thehindu.R
import com.thehindu.themain.listeners.CommunicationListernerForReadList
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.models.recyclerview.NewsRvAdapter
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), CommunicationListernerForReadList {

    lateinit var newsList: List<NewsResponse>
    lateinit var newsRvAdapter: NewsRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        PreferenceHelper.getSharedPreferences(this)
        rv_search.layoutManager = LinearLayoutManager(this)
       
        val token =
            "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    LocalConstants.API_CLIENT.getNewsByQuery(token, s.toString())
                        .enqueue(object : Callback<List<NewsResponse>> {
                            override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<List<NewsResponse>>,
                                response: Response<List<NewsResponse>>
                            ) {
                                if (response.body() != null) {
                                    newsList = response.body()!!
                                    newsRvAdapter = NewsRvAdapter(newsList, this@SearchActivity)
                                    rv_search.adapter = newsRvAdapter
                                    newsRvAdapter.notifyDataSetChanged()
                                }
                            }
                        })
                }
            }

        })
    }

    override fun save_it(id: Int) {

    }
}