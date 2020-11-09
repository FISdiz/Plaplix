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
    }

    fun loadDetailData(id:Int) {

        val call = RetrofitClient.retrofitInstance().detailedProduct(id)

        call.enqueue(object : Callback<PhoneDetail>{
            override fun onResponse(call: Call<PhoneDetail>, response: Response<PhoneDetail>) {
                Log.d("REPO_DETAIL", "${response.code()}")
                Log.d("REPO_DETAIL", "${response.body()}")
                if (response.isSuccessful) {
                    saveDetailsDb(detailConverter(response.body()!!))
                } else {
                    Log.d("REPO", "${call.request().url()}")
                }
            }

            override fun onFailure(call: Call<PhoneDetail>, t: Throwable) {
                Log.d("CALL_DETAIL", "LOAD ERROR")
                Log.d("CALL_DETAIL", t.message.toString())
            }

        })
    }

    fun saveDatabase(listPhoneEntity: List<PhoneEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDatabase.getPhoneDao().insertPhone(listPhoneEntity)
        }
    }


    fun saveDetailsDb(detailEntity: PhoneDetailEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            phoneDatabase.getPhoneDao().insertDetailPhone(detailEntity)
        }
    }

    fun getPhoneDetails(id: Int): LiveData<PhoneDetail> {
        return phoneDatabase.getPhoneDao().getSingleDetail(id)
    }
}

