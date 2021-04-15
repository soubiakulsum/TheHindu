package com.example.the_hindu.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.the_hindu.R
import com.example.the_hindu.adapters.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


/**
 * This Activity demonstrates a simple view pager where there are 3 Fragments
 */
class MainScreenActivity : AppCompatActivity(), OnTabSelectedListener {
    private var mViewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setViewPagerAdapter()
    }

    private fun initViews() {
        mViewPager = findViewById(R.id.mViewPager)
//        tabLayout = findViewById(R.id.tabLayout)
        tabLayout?.addOnTabSelectedListener(this)
    }

    /**
     * This method sets data to the View Pager Adapter class
     */
    private fun setViewPagerAdapter() {
        /*
        Please note that FragmentAdapter is not a inbuilt class, we need to create it.
         */
        val fragmentAdapter = FragmentAdapter(this)
        mViewPager!!.adapter = fragmentAdapter

        /*
        A mediator to link a TabLayout with a ViewPager2. The mediator will synchronize the ViewPager2's position
         with the selected tab when a tab is selected, and the TabLayout's scroll position when the user drags the ViewPager2.
         */

        tabLayout?.let {
            TabLayoutMediator(
                it, mViewPager!!
            )
            { tab, position -> Log.d("pravin", "onConfigureTab called") }.attach()
        }

    }

    /*
    called when the user drags or scrolls horizontally on the view pager
     */
    override fun onTabSelected(tab: TabLayout.Tab) {
        Log.d("Lloyd", "onTabSelected")
    }

    /*
    Called when the user goes to the next tab i.e unselected the previous tab
     */
    override fun onTabUnselected(tab: TabLayout.Tab) {
        Log.d("Lloyd", "onTabUnselected")
    }

    /*
    If the user clicks on the current tab
     */
    override fun onTabReselected(tab: TabLayout.Tab) {
        Log.d("Lloyd", "onTabReselected")
    }
}