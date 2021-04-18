package com.example.thehinduclone.the_hindu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thehinduclone.R
import com.example.thehinduclone.the_hindu.models.PersonaliseModel
import com.example.thehinduclone.the_hindu.recyclerView.ItemClickListener
import com.example.thehinduclone.the_hindu.recyclerView.PersonaliseAdapter
import com.example.thehinduclone.the_hindu.viewmodels.PersonalizeViewModel
import kotlinx.android.synthetic.main.activity_localize_home.*

class PersonalizeHomeActivity : AppCompatActivity() ,ItemClickListener {

    private val selectionList = mutableListOf<PersonaliseModel>()
    lateinit var mBtnNext:Button
     lateinit var personaliseAdapter: PersonaliseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize_home)

        mBtnNext = findViewById(R.id.btnPersonaliseNext)

        mBtnNext.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, LocalizeHomeActivity::class.java)
            startActivity(intent)
        })

        val viewModel = ViewModelProviders.of(this).get(PersonalizeViewModel::class.java)
        val personaliseAdapter = PersonaliseAdapter(selectionList, this)
        val manager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
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

    override fun onTemClicked(personaliseModel: PersonaliseModel, position: Int) {

        var updateModel: PersonaliseModel
        if (personaliseModel.isSelected) {
            updateModel = PersonaliseModel(personaliseModel.name, false)
        } else {
            updateModel = PersonaliseModel(personaliseModel.name, true)
        }
        selectionList.set(position, updateModel)
        personaliseAdapter.notifyItemChanged(position)


    }
}