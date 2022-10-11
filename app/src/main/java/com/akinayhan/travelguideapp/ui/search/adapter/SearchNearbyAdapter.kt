package com.akinayhan.travelguideapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.databinding.ItemGuideTopBinding
import com.akinayhan.travelguideapp.databinding.ItemSearchNearbyBinding
import com.akinayhan.travelguideapp.util.extentions.loadFromUrl

class SearchNearbyAdapter( var onClick :(Travel) -> Unit
) : RecyclerView.Adapter<SearchNearbyAdapter.ViewHolder>() {

    var travelList: List<Travel>? = null

    inner class ViewHolder(
        val binding: ItemSearchNearbyBinding
    ) : RecyclerView.ViewHolder(binding.root)

    fun updateData(list: List<Travel>) {
        travelList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchNearbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (travelList.isNullOrEmpty()) {
            return 0
        } else {
            return travelList!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            binding.apply {
                val travelItem = travelList?.get(position)
                imageNearby.loadFromUrl(travelItem!!.images?.toList()?.get(0)?.url)
                txtCategory.text = travelItem!!.category
                txtCity.text = travelItem!!.city
                txtCountry.text = travelItem!!.country
                imageNearby.setOnClickListener { onClick(travelItem) }
            }
        }
    }
}