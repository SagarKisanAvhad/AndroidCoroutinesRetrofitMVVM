package com.devtides.androidcoroutinesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtides.androidcoroutinesretrofit.model.Country
import com.devtides.androidcoroutinesretrofit.repository.CountryApi
import kotlinx.coroutines.*

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    val countryApi = CountryApi()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countryApi.getCountries()
                if (response.isSuccessful) {
                    loading.postValue(false)
                    countryLoadError.postValue("")
                    countries.postValue(response.body())
                } else {
                    onError(response.message())
                }

        }

    }


    private fun onError(message: String) {
        countryLoadError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}