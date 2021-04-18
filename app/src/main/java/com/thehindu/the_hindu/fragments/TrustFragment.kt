package com.thehindu.the_hindu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thehindu.R

class TrustFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trust, container, false)
    }

    companion object {

        var ARG_PARAM1 = ""

        fun newInstance(param1: String?) : TrustFragment {
            val trustFragment = TrustFragment()
            val args = Bundle()
            ARG_PARAM1 = param1!!
            trustFragment.arguments  = args
            return trustFragment
        }

    }
}