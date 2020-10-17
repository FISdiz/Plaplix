package cl.desafiolatam.plaplix.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "phone_table")
data class PhoneEntity (
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)