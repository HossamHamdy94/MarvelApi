package com.example.downloadexample.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.marvelapi.models.CharactersResponse
import com.example.marvelapi.repos.getChractersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class FragmentMoviesViewModel @ViewModelInject constructor(private var getFileRepo: getChractersRepo) : BaseViewModel() {



    var  getCharcterLiveData = MutableLiveData<CharactersResponse> ()
    // used when Sever Is Down for testing only

    fun getChracters () {

        getCharcterLiveData = MutableLiveData()
        compositeSubscription.add(getFileRepo.getCharcters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                getCharcterLiveData.postValue(result)
            }, { error ->
//                val generalResponse = CharactersResponse ()
//                getCharcterLiveData.postValue(generalResponse)
            }))

    }


}