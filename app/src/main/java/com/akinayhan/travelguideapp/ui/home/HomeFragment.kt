package com.akinayhan.travelguideapp.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akinayhan.travelguideapp.databinding.FragmentHomeBinding
import com.akinayhan.travelguideapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var mBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var homeImagesAdapter: HomeImagesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater)
        initBinding()
        observers()
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.filterTravel()
    }

    private fun initBinding() {

        mBinding?.apply {
            homeImagesAdapter = HomeImagesAdapter({ travelItem ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToDetailFragment(travelItem))
            })
            btnFlights.setOnClickListener {
                viewModel.filterTravel("flight")
                tabLayout.getTabAt(1)?.select()
            }
            btnHotels.setOnClickListener {
                viewModel.filterTravel("hotel")
                tabLayout.getTabAt(2)?.select()
            }
            btnCars.setOnClickListener {
                viewModel.filterTravel("transportation")
                tabLayout.getTabAt(3)?.select()
            }
            btnTaxi.setOnClickListener {
                viewModel.filterTravel("transportation")
                tabLayout.getTabAt(3)?.select()
            }
            recyclerViewHome.apply {
                adapter = homeImagesAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }

            tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    when (position) {
                        0 -> {
                            viewModel.filterTravel()
                        }
                        1 -> {
                            viewModel.filterTravel("flight")
                        }
                        2 -> {
                            viewModel.filterTravel("hotel")
                        }
                        3 -> {
                            viewModel.filterTravel("transportation")
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            }
            )

            /*
            object : CountDownTimer(2000, 1000) {
                //40000 milli seconds is total time, 1000 milli seconds is time interval
                override fun onTick(millisUntilFinished: Long) {
                    tabLayout.getTabAt(3)!!.select()
                }
                override fun onFinish() {
                    tabLayout.getTabAt(0)!!.select()
                }
            }.start()


             */

        }
    }

    private fun observers() {
        viewModel.apply {
            travelResponseLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    homeImagesAdapter?.updateData(liste)
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
