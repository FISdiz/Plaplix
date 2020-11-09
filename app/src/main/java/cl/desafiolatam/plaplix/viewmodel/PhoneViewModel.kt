package cl.desafiolatam.plaplix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.desafiolatam.plaplix.model.Repository
import cl.desafiolatam.plaplix.model.database.PhoneDetailEntity
import cl.desafiolatam.plaplix.model.pojo.Phone
import cl.desafiolatam.plaplix.model.pojo.PhoneDetail

class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository = Repository(application)
    var phoneSmallList = repository.phoneSmallList
    lateinit var result : LiveData<PhoneDetail>

    init {
        repository = Repository(application)
        repository.loadApiData()
    }

    private val selectedPhone = MutableLiveData<Phone>()

    fun selectedID(phone : Phone) {
        selectedPhone.value = phone
        repository.loadDetailData(phone.id)
        result = repository.getPhoneDetails(phone.id)
    }
}