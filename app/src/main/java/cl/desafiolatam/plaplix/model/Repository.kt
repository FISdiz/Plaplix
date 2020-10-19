package cl.desafiolatam.plaplix.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import cl.desafiolatam.plaplix.model.database.PhoneDatabase
import cl.desafiolatam.plaplix.model.database.PhoneDetailEntity
import cl.desafiolatam.plaplix.model.database.PhoneEntity
import cl.desafiolatam.plaplix.model.pojo.Phone
import cl.desafiolatam.plaplix.model.pojo.PhoneDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    var phoneDatabase = PhoneDatabase.getDatabase(context)
    var phoneSmallList = phoneDatabase.getPhoneDao().getSmallInfo()

    fun loadApiData() {
        val call = RetrofitClient.retrofitInstance().allProducts()

        call.enqueue(object : Callback<List<Phone>> {
            override fun onFailure(call: Call<List<Phone>>, t: Throwable) {
                Log.d("CALL", "LOAD ERROR")
                Log.d("CALL", t.message.toString())
            }

            override fun onResponse(call: Call<List<Phone>>, response: Response<List<Phone>>) {
                Log.d("REPO", "${response.code()}")
                Log.d("REPO", "${response.body()}")
                if (response.isSuccessful) {
                    saveDatabase(phoneConverter(response.body()!!))
                } else {
                    Log.d("REPO", "${call.request().url()}")
                }
            }
        })

        val callDetail = RetrofitClient.retrofitInstance().allDetailedProduct()

        callDetail.enqueue(object : Callback<List<PhoneDetail>> {
            override fun onFailure(call: Call<List<PhoneDetail>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<PhoneDetail>>, response: Response<List<PhoneDetail>>) {
                saveDetailsDb(detailConverter(response.body()!!))
            }
        })
    }

    fun phoneConverter(phoneList: List<Phone>): List<PhoneEntity> {
        return phoneList.map { phone ->
            PhoneEntity(
                    phone.id,
                    phone.image,
                    phone.name,
                    phone.price
            )
        }
    }

    fun saveDatabase(listPhoneEntity: List<PhoneEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDatabase.getPhoneDao().insertPhone(listPhoneEntity)
        }
    }

    fun detailConverter(detailList: List<PhoneDetail>): List<PhoneDetailEntity> {
        return detailList.map { detail ->
            PhoneDetailEntity(
                    detail.id,
                    detail.image,
                    detail.name,
                    detail.price,
                    detail.credit,
                    detail.description,
                    detail.lastPrice
            )
        }
    }
    fun saveDetailsDb(listDetailEntity: List<PhoneDetailEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDatabase.getPhoneDao().insertDetailPhone(listDetailEntity)
        }
    }

    fun getPhoneDetails(param1: String): LiveData<PhoneDetailEntity> {
        return phoneDatabase.getPhoneDao().getSingleDetail(param1)
    }
}

