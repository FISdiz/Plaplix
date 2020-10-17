package cl.desafiolatam.plaplix.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.desafiolatam.plaplix.model.pojo.Phone

@Dao
interface PhoneDao {

    @Query("SELECT * FROM phone_table")
    fun getSmallInfo() : LiveData<List<Phone>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneList : List<PhoneEntity>)

/*    @Query("SELECT * FROM phone_table WHERE id=:id")
    fun getAllInfo(id:Int) : LiveData<List<PhoneDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailPhone(phoneList : List<PhoneDetailEntity>)*/

}