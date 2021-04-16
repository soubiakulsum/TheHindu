package com.example.thehinduclone.kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.thehinduclone.R
import kotlinx.android.synthetic.main.activity_briefing.*

class BriefingActivity : AppCompatActivity() {

    val editions = arrayOf("All Editions","Morning Editions","Noon Editions","Evening Editions")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_briefing)

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,editions)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}