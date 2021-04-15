package com.example.the_hindu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.the_hindu.fragments.AdFreeFragment
import com.example.the_hindu.fragments.SupportFragment
import com.example.the_hindu.fragments.TrustFragment

/**
 * This class gives the fragments as the user swipes the screen horizontally
 */
class FragmentAdapter(fragmentActivity: FragmentActivity?) :
    FragmentStateAdapter(fragmentActivity!!) {
    /**
     * This method is called when the user swipes the screen horizontally
     *
     * @param position position of the screen user wants to see
     * @return returns a fragment based on the position
     */
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SupportFragment.newInstance("This is First Fragment, So Welcome to circle indicators. Hope you are doing Amazing")
            1 -> return AdFreeFragment.newInstance("This is second Fragment")
            2 -> return TrustFragment.newInstance("This is Third Fragment")
        }
        return SupportFragment.newInstance("This is Default Fragment")
    }

    override fun getItemCount(): Int {
        return 3
    }
}
