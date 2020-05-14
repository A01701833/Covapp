package com.itesm.covapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment: Fragment() {

    companion object{
        fun newInstance(): HomeFragment{
            val frag = HomeFragment()

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick(){
        btnAddPost.setOnClickListener {
            Intents.goToMakePost(activity!!)
        }
    }
}