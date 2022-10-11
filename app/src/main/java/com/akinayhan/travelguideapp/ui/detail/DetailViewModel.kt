package com.akinayhan.travelguideapp.ui.detail

import androidx.lifecycle.viewModelScope
import com.akinayhan.travelguideapp.data.model.travel.Travel
import com.akinayhan.travelguideapp.data.repository.TravelRepository
import com.akinayhan.travelguideapp.ui.base.BaseViewModel
import com.akinayhan.travelguideapp.util.SingleLiveEvent
import com.akinayhan.travelguideapp.util.constants.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TravelRepository
): BaseViewModel(){

    var updateAnswer = SingleLiveEvent<Boolean>()

    fun updateBookmark(travel: Travel) {
        viewModelScope.launch {
            travel.let {
                repository.editTravel(travel).collect {
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
                            isPageLoaded.postValue(true)
                            isLoading.postValue(false)
                            updateAnswer.postValue(true)
                        }
                    }
                }
            }
        }
    }



}