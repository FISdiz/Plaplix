package cl.desafiolatam.plaplix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import cl.desafiolatam.plaplix.model.Repository
import cl.desafiolatam.plaplix.model.database.PhoneDetailEntity

class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository =
        Repository(application)
    var phoneSmallList = repository.phoneSmallList

    init {
        repository = Repository(application)
        repository.loadApiData()
    }

    fun getDetailFrom(param1: String) : LiveData<PhoneDetailEntity> {
        return repository.getPhoneDetails(param1)
    }

}