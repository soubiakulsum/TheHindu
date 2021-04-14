package com.example.the_hindu.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.the_hindu.R
import com.example.the_hindu.model.PersonaliseModel
import com.example.the_hindu.recycler.PersonaliseAdapter
import com.example.the_hindu.viewModels.PersonalizeViewModel
import kotlinx.android.synthetic.main.activity_personalize_home.*

class PersonalizeHomeActivity : AppCompatActivity() {

    private val selectionList = mutableListOf<PersonaliseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize_home)


        val viewModel = ViewModelProviders.of(this).get(PersonalizeViewModel::class.java)
        val personaliseAdapter = PersonaliseAdapter(selectionList)
        val manager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        personaliseRecycler.apply {
            layoutManager = manager
            adapter = personaliseAdapter
        }

        viewModel.getCategoryName()


        viewModel.getList().observe(this, Observer {
            selectionList.addAll(it)
            personaliseAdapter.notifyDataSetChanged()
        })


    }
}