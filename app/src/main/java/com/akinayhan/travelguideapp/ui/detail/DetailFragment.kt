package com.akinayhan.travelguideapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akinayhan.travelguideapp.R
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.databinding.FragmentDetailBinding
import com.akinayhan.travelguideapp.util.extentions.loadFromUrl
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var mBinding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private var detailImagesAdapter: DetailImagesAdapter? = null

    var travelItem: Travel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(inflater)

        initBinding()
        fillDatas()
        initObservers()
        return mBinding.root
    }

    private fun initObservers()
    {
        detailViewModel.apply {

            errorMessageLiveData.observe(viewLifecycleOwner, Observer {
                it?.let {
                    mBinding?.apply {
                        Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                    }
                }
            })

            updateAnswer.observe(viewLifecycleOwner, Observer {
                showSuccessfullToast()
            })

        }

    }

    private fun fillDatas() {
        mBinding?.apply {
            arguments?.apply {
                travelItem = DetailFragmentArgs.fromBundle(this).travelModel
                travelItem?.also {
                    mBinding.txtCity.text = it.city
                    mBinding.txtCountry.text = it.country
                    mBinding.imageDetail.loadFromUrl(it.images?.toList()?.get(0)?.url)
                    detailImagesAdapter?.updateData(it.images!!.toList())
                    //mBinding.txtCity.text = it.isBookmark.toString()
                } ?: kotlin.run {
                    //TODO: Item Null
                }
            }
        }
    }



    private  fun showSuccessfullToast()
    {
        Toast.makeText(requireContext(), R.string.successfull, Toast.LENGTH_LONG).show()
    }


    private fun initBinding() {

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE

        mBinding?.apply {
            detailImagesAdapter = DetailImagesAdapter({ imageItem ->
                imageDetail.loadFromUrl(imageItem.url)
            })
            recyclerViewImages.apply {
                adapter = detailImagesAdapter
                itemAnimator = DefaultItemAnimator()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }

            buttonAddBookmark.setOnClickListener {
                travelItem?.isBookmark = travelItem?.isBookmark!!.not()
                detailViewModel.updateBookmark(travelItem!!)
            }


        }
    }
}