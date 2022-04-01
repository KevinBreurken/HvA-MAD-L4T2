package nl.hva.madlevel4task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import nl.hva.madlevel4task1.GameActionResult
import java.util.*

@Entity(tableName = "productTable")
data class Product(

    @ColumnInfo(name = "result")
    var result: GameActionResult,

    @ColumnInfo(name = "createdDate")
    var createdDate: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)
