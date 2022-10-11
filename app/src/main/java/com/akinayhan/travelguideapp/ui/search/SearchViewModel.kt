package com.akinayhan.travelguideapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.data.repository.TravelRepository
import com.akinayhan.travelguideapp.ui.base.BaseViewModel
import com.akinayhan.travelguideapp.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: TravelRepository
):BaseViewModel() {

    var allList : List<Travel>?=null

    init {
        getAllTravels()
    }

    var searcTopDestinationLiveData = MutableLiveData<List<Travel>>()
    var searchNearbyAdapterLiveData = MutableLiveData<List<Travel>>()

    fun updateAdapterDatas(){
        allList?.filter {it.category.equals("topdestination")}.let {
            searcTopDestinationLiveData.postValue(it)
        }
        allList?.filter {it.category.equals("nearby")}.let {
            searchNearbyAdapterLiveData.postValue(it)
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
}