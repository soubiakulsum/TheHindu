package com.thehindu.the_hindu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thehindu.the_hindu.fragments.AdFreeFragment
import com.thehindu.the_hindu.fragments.BriefingFragment
import com.thehindu.the_hindu.fragments.SupportFragment
import com.thehindu.the_hindu.fragments.TrustFragment

class FragmentAdapter(fragmentActivity: FragmentActivity?) :
        FragmentStateAdapter(fragmentActivity!!) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SupportFragment.newInstance("This is First Fragment")
            1 -> return BriefingFragment.newInstance("This is second Fragment")
            2 -> return AdFreeFragment.newInstance("This is Third Fragment")
            3 -> return TrustFragment.newInstance("This is Fourth Fragment")
        }
        return SupportFragment.newInstance("This is Default Fragment")
    }

    override fun getItemCount(): Int {
        return 4
    }
}
