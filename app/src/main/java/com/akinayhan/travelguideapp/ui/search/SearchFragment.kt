package com.akinayhan.travelguideapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akinayhan.travelguideapp.R
import com.akinayhan.travelguideapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import com.akinayhan.travelguideapp.databinding.FragmentGuideBinding
import com.akinayhan.travelguideapp.ui.base.BaseFragment
import com.akinayhan.travelguideapp.ui.guide.GuideFragmentDirections
import com.akinayhan.travelguideapp.ui.guide.GuideViewModel
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideCategoryAdapter
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideMightAdapter
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideTopPickAdapter
import com.akinayhan.travelguideapp.ui.home.HomeFragmentDirections
import com.akinayhan.travelguideapp.ui.search.adapter.SearchNearbyAdapter
import com.akinayhan.travelguideapp.ui.search.adapter.SearchTopAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private lateinit var mBinding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private var searchTopAdapter: SearchTopAdapter? = null
    private var searchNearbyAdapter: SearchNearbyAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSearchBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        observers()
    }

    private fun initBinding() {
        mBinding?.apply {
            searchTopAdapter = SearchTopAdapter ({ travelItem ->
                findNavController().navigate(
                    SearchFragmentDirections.actionNavigationSearchToDetailFragment(travelItem))
            })
            searchNearbyAdapter = SearchNearbyAdapter ({ travelItem ->
                findNavController().navigate(
                    SearchFragmentDirections.actionNavigationSearchToDetailFragment(travelItem))
            })
            recyclerViewTop.apply {
                adapter = searchTopAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            recyclerViewNearby.apply {
                adapter = searchNearbyAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    private fun observers() {
        viewModel.apply {
            searcTopDestinationLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    searchTopAdapter?.updateData(liste)
                }
            })
            searchNearbyAdapterLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    searchNearbyAdapter?.updateData(liste)
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