package nl.hva.madlevel4task1.repository

import android.content.Context
import nl.hva.madlevel4task1.database.HistoryListRoomDatabase
import nl.hva.madlevel4task1.dao.HistoryDao
import nl.hva.madlevel4task1.model.History

class HistoryRepository(context: Context) {

    private val historyDao: HistoryDao

    init {
        val database = HistoryListRoomDatabase.getDatabase(context)
        historyDao = database!!.productDao()
    }

    suspend fun getAllProducts(): List<History> {
        return historyDao.getAllProducts()
    }

    suspend fun insertProduct(history: History) {
        historyDao.insertProduct(history)
    }

    suspend fun deleteProduct(history: History) {
        historyDao.deleteProduct(history)
    }

    suspend fun deleteAllProducts() {
        historyDao.deleteAllProducts()
    }
}