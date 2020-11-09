package cl.desafiolatam.plaplix.model

import cl.desafiolatam.plaplix.model.pojo.Phone
import cl.desafiolatam.plaplix.model.pojo.PhoneDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SmartphoneApi {
    @GET("products/")
    fun allProducts() : Call<List<Phone>>

    @GET("details/{id}")
    fun detailedProduct(@Path("id") id:Int) : Call<PhoneDetail>
}