package nl.hva.madlevel4task1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.hva.madlevel4task1.model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM productTable")
    suspend fun getAllProducts(): List<History>

    @Insert
    suspend fun insertProduct(history: History)

    @Delete
    suspend fun deleteProduct(history: History)

    @Query("DELETE FROM productTable")
    suspend fun deleteAllProducts()

}