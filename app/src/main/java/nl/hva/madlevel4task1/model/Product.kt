package nl.hva.madlevel4task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTable")
data class Product(

    @ColumnInfo(name = "name")
    var resultText: String,

    @ColumnInfo(name = "quantity")
    var productQuantity: Short,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)
