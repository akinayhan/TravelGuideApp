package com.akinayhan.travelguideapp.ui.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akinayhan.travelguideapp.R
import com.akinayhan.travelguideapp.databinding.FragmentTripBinding
import com.akinayhan.travelguideapp.ui.base.BaseFragment
import com.akinayhan.travelguideapp.ui.home.HomeFragmentDirections
import com.akinayhan.travelguideapp.ui.trip.adapter.TripBookmarkAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripFragment : BaseFragment() {
    private lateinit var mBinding: FragmentTripBinding
    private val viewModel: TripViewModel by viewModels()
    private var tripBookmarkAdapter: TripBookmarkAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTripBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        observers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMyBookMarks()
    }

    private fun initBinding() {

        mBinding?.apply {
            tripBookmarkAdapter = TripBookmarkAdapter ({ travelItem ->
                findNavController().navigate(
                    TripFragmentDirections.actionNavigationTripToDetailFragment(travelItem))

            })
            recyclerView.apply {
                adapter = tripBookmarkAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }

            btnAdd.setOnClickListener {
                viewModel.addData()
                viewModel.getMyTrips()
                Toast.makeText(context, R.string.data_added,Toast.LENGTH_SHORT).show()
            }

            tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    when (position) {
                        0 -> {
                            viewModel.getMyTrips()
                            btnAdd.visibility = View.VISIBLE
                        }
                        1 -> {
                            viewModel.getMyBookMarks()
                            btnAdd.visibility = View.GONE
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            }
            )
        }
    }

    private fun observers() {
        viewModel.apply {
            travelResponseLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    tripBookmarkAdapter?.updateData(liste)
                }
            })

            errorMessageLiveData.observe(viewLifecycleOwner, Observer {
                it?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    mBinding?.apply {
                        if (it) {
                            //progressBar.show()
                        } else {
                            //progressBar.gone()
                        }
                    }
                }
            })

        }

    }
}