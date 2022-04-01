package nl.hva.madlevel4task1.database

import android.content.Context
import androidx.room.*
import nl.hva.madlevel4task1.Converters
import nl.hva.madlevel4task1.dao.HistoryDao
import nl.hva.madlevel4task1.model.History

@Database(entities = [History::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HistoryListRoomDatabase : RoomDatabase() {

    abstract fun productDao(): HistoryDao

    companion object {
        private const val DATABASE_NAME = "SHOPPING_LIST_DATABASE"

        @Volatile
        private var historyListRoomDatabaseInstance: HistoryListRoomDatabase? = null

        fun getDatabase(context: Context): HistoryListRoomDatabase? {
            if (historyListRoomDatabaseInstance == null) {
                synchronized(HistoryListRoomDatabase::class.java) {
                    if (historyListRoomDatabaseInstance == null) {
                        historyListRoomDatabaseInstance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                HistoryListRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return historyListRoomDatabaseInstance
        }
    }
}