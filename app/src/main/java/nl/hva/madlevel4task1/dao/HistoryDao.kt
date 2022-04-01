package nl.hva.madlevel4task1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.hva.madlevel4task1.model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM historyTable")
    suspend fun getAllHistory(): List<History>

    @Insert
    suspend fun insertHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

    @Query("DELETE FROM historyTable")
    suspend fun deleteAllHistory()

}