package com.akinayhan.travelguideapp.ui.trip

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akinayhan.travelguideapp.data.model.travel.Image
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.data.repository.TravelRepository
import com.akinayhan.travelguideapp.data.repository.TripRepository
import com.akinayhan.travelguideapp.ui.base.BaseViewModel
import com.akinayhan.travelguideapp.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(private val tripRepository: TripRepository,
                                        private val travelRepository: TravelRepository
):BaseViewModel() {

    var allList : List<Travel>?=null

    init {
        getAllTravels()
    }

    var travelResponseLiveData = MutableLiveData<List<Travel>>()


    fun getMyBookMarks(){
            allList?.filter {it.isBookmark.equals(true)}.let {
                travelResponseLiveData.postValue(it)
        }
    }

    fun addData(){
        var imageItems = listOf<Image>(
            Image(url = "https://cdn.impala.travel/properties/cknec4k5j008t0uo5828shuij.jpg"),
            )
        var travelItem = Travel(
            city = "23 April - 19 May",
            country = "Neptune",
            images = imageItems,
            category = "Hotel"
            )

        viewModelScope.launch {

            tripRepository.addTrip(travelItem).collect {

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

                    }
                }
            }
        }
    }

    fun getMyTrips() {

        viewModelScope.launch {

            tripRepository.getMyTrips().collect {

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
                            travelResponseLiveData.postValue(it)

                        }
                    }
                }
            }
        }
    }

    private fun getAllTravels() {

        viewModelScope.launch {

            travelRepository.getAllTravels().collect {

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