package cl.desafiolatam.plaplix.model.pojo

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)