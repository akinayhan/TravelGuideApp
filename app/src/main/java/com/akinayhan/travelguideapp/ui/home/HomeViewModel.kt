package com.akinayhan.travelguideapp.ui.home

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
class HomeViewModel @Inject constructor(private val repository: TravelRepository
):BaseViewModel() {

    var allList : List<Travel>?=null

    init {
        getAllTravels()
    }

    var travelResponseLiveData = MutableLiveData<List<Travel>>()

    fun filterTravel(category:String = ""){
        if (category.isEmpty()) {
            travelResponseLiveData.postValue(allList)
        } else{
            allList?.filter {it.category.equals(category)}.let {
                travelResponseLiveData.postValue(it)
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

                        }
                    }
                }
            }
        }
    }
}