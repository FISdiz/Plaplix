package cl.desafiolatam.plaplix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cl.desafiolatam.plaplix.model.Repository

class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository =
        Repository(application)
    var phoneSmallList = repository.phoneSmallList

    init {
        repository = Repository(application)
        repository.loadApiData()
    }

}