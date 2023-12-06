package com.example.finalproject.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    protected var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    protected var _exceptionLiveData = MutableLiveData<String?>()
    val exceptionLiveData: LiveData<String?> = _exceptionLiveData

    fun <T> launch(
        request: suspend () -> T,
        onSuccess: (T) -> Unit = { }
    ) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    request.invoke()
                }
                onSuccess.invoke(response)
            } catch (e: Exception) {
                _exceptionLiveData.postValue(e.message)
                Log.e(">>>", e.message.orEmpty())
            }
        }
    }

    fun <T1, T2> launch(
        doubleRequest: suspend () -> Pair<T1, T2>,
        onSuccess: (T1, T2) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    doubleRequest.invoke()
                }
                onSuccess.invoke(response.first, response.second)
            } catch (e: Exception) {
                _exceptionLiveData.postValue(e.message)
                Log.e(">>>", e.message.orEmpty())
            }
        }
    }


}