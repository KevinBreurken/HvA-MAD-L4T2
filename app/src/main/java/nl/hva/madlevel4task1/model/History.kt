package nl.hva.madlevel4task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import nl.hva.madlevel4task1.GameActionResult
import nl.hva.madlevel4task1.GameActionType
import java.util.*

@Entity(tableName = "historyTable")
data class History(

    @ColumnInfo(name = "result")
    var result: GameActionResult,

    @ColumnInfo(name = "computerAction")
    var computerAction: GameActionType,

    @ColumnInfo(name = "playerAction")
    var playerAction: GameActionType,

    @ColumnInfo(name = "createdDate")
    var createdDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)
