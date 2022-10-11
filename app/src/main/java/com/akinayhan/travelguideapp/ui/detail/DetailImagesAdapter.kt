package com.akinayhan.travelguideapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akinayhan.travelguideapp.data.model.travel.Image
import com.akinayhan.travelguideapp.databinding.ItemDetailRecyclerBinding
import com.akinayhan.travelguideapp.util.extentions.loadFromUrl

class DetailImagesAdapter(var onClick :(Image) -> Unit
) : RecyclerView.Adapter<DetailImagesAdapter.ViewHolder>() {

    var imageList: List<Image>? = null
    inner class ViewHolder(val binding: ItemDetailRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root)

    fun updateData(list : List<Image>){
        imageList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if(imageList.isNullOrEmpty()){
            return 0
        }else{
            return imageList!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){

            binding.apply {
                val imageItem = imageList?.get(position)
                imgItem.loadFromUrl(imageItem?.url)
                imgItem.setOnClickListener { onClick(imageItem!!) }
            }
        }
    }
}
