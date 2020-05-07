package com.itesm.covapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.covapp.R

class PlaceFragment: Fragment()  {
    companion object {
        fun newInstance(): PlaceFragment{
            val frag = PlaceFragment()

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_place,container, false)


    }
}