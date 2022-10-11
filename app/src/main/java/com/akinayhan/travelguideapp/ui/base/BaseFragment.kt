package com.akinayhan.travelguideapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akinayhan.travelguideapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseFragment:Fragment() {

    override fun onResume() {
        super.onResume()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.VISIBLE
    }
}