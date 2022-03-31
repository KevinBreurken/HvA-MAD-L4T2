package nl.hva.madlevel4task1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.hva.madlevel4task1.databinding.FragmentHistoryBinding
import nl.hva.madlevel4task1.model.Product
import nl.hva.madlevel4task1.repository.ProductRepository
import nl.hva.madlevel4task1.ui.old.ShoppingListAdapter

class HistoryFragment: Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var productRepository: ProductRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val products = arrayListOf<Product>()
    private val shoppingListAdapter = ShoppingListAdapter(products)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepository = ProductRepository(requireContext())
        getShoppingListFromDatabase()

        initRv()

//        binding.fabAddProduct.setOnClickListener {
//            showAddProductDialog()
//        }
//        binding.fabDeleteAll.setOnClickListener {
//            removeAllProducts()
//        }
    }

    private fun initRv() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.historyList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = shoppingListAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

        }
    }

    private fun getShoppingListFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            products.clear()
            products.addAll(shoppingList)
            shoppingListAdapter.notifyDataSetChanged()
        }
    }

}