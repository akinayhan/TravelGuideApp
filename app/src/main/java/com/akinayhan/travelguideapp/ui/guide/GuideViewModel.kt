package com.akinayhan.travelguideapp.ui.guide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akinayhan.travelguideapp.data.model.categories.GuideCategory
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.data.repository.TravelRepository
import com.akinayhan.travelguideapp.ui.base.BaseViewModel
import com.akinayhan.travelguideapp.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(private val repository: TravelRepository
):BaseViewModel() {

    var allList : List<Travel>?=null

    init {
        getAllTravels()
        getCategory()
    }

    var migthAdapterLiveData = MutableLiveData<List<Travel>>()
    var topPickAdapterLiveData = MutableLiveData<List<Travel>>()
    var allCategoriesLiveData = MutableLiveData<List<GuideCategory>>()

    fun updateAdapterDatas(){
        allList?.filter {it.category.equals("mightneed")}.let {
            migthAdapterLiveData.postValue(it)
        }
        allList?.filter {it.category.equals("toppick")}.let {
            topPickAdapterLiveData.postValue(it)
        }
    }

    fun updateTopPickAdapterData(position: Int){
        when (position%4){
            0 -> {
                allList?.filter {it.category.equals("transportation")}.let {
                    topPickAdapterLiveData.postValue(it)
                }
            }
            1 -> {
                allList?.filter {it.category.equals("flight")}.let {
                    topPickAdapterLiveData.postValue(it)
                }
            }
            2->{
                allList?.filter {it.category.equals("hotel")}.let {
                    topPickAdapterLiveData.postValue(it)
                }
            }
            else ->{
                allList?.filter {it.category.equals("toppick")}.let {
                    topPickAdapterLiveData.postValue(it)
                }
            }

        }
    }

    private fun getAllTravels() {

        viewModelScope.launch {

            repository.getAllTravels().collect {

                when (it) {

                    is Resource.Loading -> {
                        isPageLoaded.postValue(false)
                        isLoading.postValue(true)
                    }

                    is Resource.Error -> {
                        isLoading.postValue(false)
                        errorMessageLiveData.postValue(it.errorMessage)
                    }

                    is Resource.Success -> {

                        isLoading.postValue(false)
                        isPageLoaded.postValue(true)

                        it.data?.let {
                            allList = it
                            updateAdapterDatas()

                        }
                    }
                }
            }
        }
    }

    private fun getCategory() {

        viewModelScope.launch {

            repository.getCategories().collect {

                when (it) {

                    is Resource.Loading -> {
                        isPageLoaded.postValue(false)
                        isLoading.postValue(true)
                    }

                    is Resource.Error -> {
                        isLoading.postValue(false)
                        errorMessageLiveData.postValue(it.errorMessage)
                    }

                    is Resource.Success -> {

                        isLoading.postValue(false)
                        isPageLoaded.postValue(true)

                        it.data?.let {
                            allCategoriesLiveData.postValue(it)
                        }
                    }
                }
            }
        }
    }
}