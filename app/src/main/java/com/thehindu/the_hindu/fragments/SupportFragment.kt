package com.thehindu.the_hindu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thehindu.R


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class SupportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trust, container, false)
    }

    companion object {

        var ARG_PARAM1 = ""

        fun newInstance(param1: String?) : SupportFragment{
            val supportFragment = SupportFragment()
            val args = Bundle()
            ARG_PARAM1 = param1!!
            supportFragment.arguments  = args
            return supportFragment
        }

    }


}