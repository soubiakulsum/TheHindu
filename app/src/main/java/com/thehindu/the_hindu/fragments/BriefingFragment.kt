package com.thehindu.the_hindu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thehindu.R

class BriefingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_briefing, container, false)
    }
    companion object {

        var ARG_PARAM1 = ""

        fun newInstance(param1: String?) : BriefingFragment {
            val briefingFragment = BriefingFragment()
            val args = Bundle()
            ARG_PARAM1 = param1!!
            briefingFragment.arguments  = args
            return briefingFragment
        }

    }
}