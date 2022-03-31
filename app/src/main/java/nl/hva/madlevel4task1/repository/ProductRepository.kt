package nl.hva.madlevel4task1.repository

import android.content.Context
import nl.hva.madlevel4task1.database.ShoppingListRoomDatabase
import nl.hva.madlevel4task1.dao.ProductDao
import nl.hva.madlevel4task1.model.Product

class ProductRepository(context: Context) {

    private val productDao: ProductDao

    init {
        val database = ShoppingListRoomDatabase.getDatabase(context)
        productDao = database!!.productDao()
    }

    suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }
}