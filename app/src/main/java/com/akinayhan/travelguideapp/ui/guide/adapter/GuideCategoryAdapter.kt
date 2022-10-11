package com.akinayhan.travelguideapp.ui.guide.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akinayhan.travelguideapp.R
import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.databinding.ItemGuideCategoryBinding

class GuideCategoryAdapter(var onClick :(Int,String) -> Unit
) : RecyclerView.Adapter<GuideCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemGuideCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var categoryList: List<GuideCategory>? = null

    fun updateData(list: List<GuideCategory>) {
        categoryList = list
        notifyDataSetChanged()
    }

    fun getIcon(context: Context,id: String): Drawable{
        when(id){
            "1" -> return context.resources.getDrawable(R.drawable.ic_taxi_cat1)
            "2" -> return context.resources.getDrawable(R.drawable.ic_rentcar_cat2)
            "3" -> return context.resources.getDrawable(R.drawable.ic_museum_cat3)
            "4" -> return context.resources.getDrawable(R.drawable.ic_restaurant_cat4)
            "5" -> return context.resources.getDrawable(R.drawable.ic_resort_cat5)
            "6" -> return context.resources.getDrawable(R.drawable.ic_mall_cat6)
            else -> return context.resources.getDrawable(R.drawable.ic_sightseei_cat7)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGuideCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (categoryList.isNullOrEmpty()) {
            return 0
        } else {
            return categoryList!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            binding.apply {
                cnsRoot.setOnClickListener {
                    onClick(position,categoryList?.get(position)!!.title)
                }
                txtCategory.text = categoryList?.get(position)!!.title
                iconBookMark.setImageDrawable(getIcon(iconBookMark.context,categoryList?.get(position)!!.id))
            }
        }
    }
}