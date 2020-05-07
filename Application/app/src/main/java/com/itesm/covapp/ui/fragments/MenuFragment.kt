package com.itesm.covapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itesm.covapp.R
import com.itesm.covapp.utils.Intents
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: Fragment() {
    companion object {
        fun newInstance(): MenuFragment{
            val frag = MenuFragment()

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu,container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick(){
        btnSettingsProfile.setOnClickListener {
            Intents.goToProfile(activity!!)
        }
    }
}