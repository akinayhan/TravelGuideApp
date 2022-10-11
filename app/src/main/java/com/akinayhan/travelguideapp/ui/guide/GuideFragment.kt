package com.akinayhan.travelguideapp.ui.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akinayhan.travelguideapp.databinding.FragmentGuideBinding
import com.akinayhan.travelguideapp.ui.base.BaseFragment
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideCategoryAdapter
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideMightAdapter
import com.akinayhan.travelguideapp.ui.guide.adapter.GuideTopPickAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : BaseFragment() {
    private lateinit var mBinding: FragmentGuideBinding
    private val viewModel: GuideViewModel by viewModels()
    private var guideTopPickAdapter: GuideTopPickAdapter? = null
    private var guideMightAdapter: GuideMightAdapter? = null
    private var guideCategoryAdapter: GuideCategoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGuideBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        observers()
    }

    private fun initBinding() {
        mBinding?.apply {
            guideMightAdapter = GuideMightAdapter ({ travelItem ->
                findNavController().navigate(
                    GuideFragmentDirections.actionNavigationGuideToDetailFragment(travelItem))
            })
            guideTopPickAdapter = GuideTopPickAdapter({ travelItem ->
                findNavController().navigate(
                    GuideFragmentDirections.actionNavigationGuideToDetailFragment(travelItem))
            })
            guideCategoryAdapter = GuideCategoryAdapter ({ pos,name ->
                txtTopPick.text = name
                viewModel.updateTopPickAdapterData(pos)
                            })
            recyclerViewMight.apply {
                adapter = guideMightAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            recyclerViewTopPick.apply {
                adapter = guideTopPickAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            recyclerViewCategory.apply {
                adapter = guideCategoryAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
        }
    }


    private fun observers() {
        viewModel.apply {
            topPickAdapterLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    guideTopPickAdapter?.updateData(liste)
                }
            })
            migthAdapterLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    guideMightAdapter?.updateData(liste)
                }
            })

            errorMessageLiveData.observe(viewLifecycleOwner, Observer {
                it?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })

            allCategoriesLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { liste ->
                    guideCategoryAdapter?.updateData(liste)
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