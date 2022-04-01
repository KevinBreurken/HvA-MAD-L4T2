package nl.hva.madlevel4task1.repository

import android.content.Context
import nl.hva.madlevel4task1.database.HistoryListRoomDatabase
import nl.hva.madlevel4task1.dao.HistoryDao
import nl.hva.madlevel4task1.model.History

class HistoryRepository(context: Context) {

    private val historyDao: HistoryDao

    init {
        val database = HistoryListRoomDatabase.getDatabase(context)
        historyDao = database!!.historyDao()
    }

    suspend fun getAllHistory(): List<History> {
        return historyDao.getAllHistory()
    }

    suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    suspend fun deleteHistory(history: History) {
        historyDao.deleteHistory(history)
    }

    suspend fun deleteAllHistory() {
        historyDao.deleteAllHistory()
    }
}