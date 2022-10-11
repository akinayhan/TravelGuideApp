package com.akinayhan.travelguideapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akinayhan.travelguideapp.util.SingleLiveEvent


abstract class BaseViewModel : ViewModel() {
    var isLoading = SingleLiveEvent<Boolean>()
    var errorMessageLiveData = SingleLiveEvent<String>()
    var isPageLoaded = MutableLiveData<Boolean>(false)
}