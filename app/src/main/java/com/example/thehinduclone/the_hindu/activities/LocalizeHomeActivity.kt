package com.example.thehinduclone.the_hindu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.thehinduclone.R
import com.example.thehinduclone.the_hindu.models.PersonaliseModel
import com.example.thehinduclone.the_hindu.recyclerView.ItemClickListener
import com.example.thehinduclone.the_hindu.recyclerView.PersonaliseAdapter
import com.example.thehinduclone.the_hindu.viewmodels.PersonalizeViewModel
import com.example.thehinduclone.ui.home.SideNavActivity
import kotlinx.android.synthetic.main.activity_localize_home.*

class LocalizeHomeActivity : AppCompatActivity(),ItemClickListener {

    private val selectionList = mutableListOf<PersonaliseModel>()

    lateinit var mBtnPrevious:Button
    lateinit var mBtnSave:Button
    lateinit var personaliseAdapter:PersonaliseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localize_home)

        mBtnPrevious = findViewById(R.id.btnPrevious)
        mBtnSave = findViewById(R.id.btnSave)

        mBtnPrevious.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext,PersonalizeHomeActivity::class.java)
            startActivity(intent)
        })

        mBtnSave.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext,SideNavActivity::class.java)
            startActivity(intent)
        })

        val viewModel = ViewModelProviders.of(this).get(PersonalizeViewModel::class.java)
        val personaliseAdapter = PersonaliseAdapter(selectionList,this)
        val manager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        personaliseRecycler.apply {
            layoutManager = manager
            adapter = personaliseAdapter
        }

        viewModel.getStateNAme()


        viewModel.getList().observe(this, Observer{
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
        personaliseAdapter.notifyDataSetChanged()


    }
}