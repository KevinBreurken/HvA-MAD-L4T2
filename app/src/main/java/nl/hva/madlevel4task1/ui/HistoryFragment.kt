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
import nl.hva.madlevel4task1.model.History
import nl.hva.madlevel4task1.repository.HistoryRepository

class HistoryFragment: Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var historyRepository: HistoryRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val products = arrayListOf<History>()
    private val shoppingListAdapter = HistoryListAdapter(products)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyRepository = HistoryRepository(requireContext())
        getShoppingListFromDatabase()

        initRv()

        binding.fabDeleteAll.setOnClickListener{
            removeAllProducts();
        }
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

    private fun removeAllProducts() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                historyRepository.deleteAllHistory()
            }
            getShoppingListFromDatabase()
        }
    }

    private fun getShoppingListFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                historyRepository.getAllHistory()
            }
            products.clear()
            products.addAll(shoppingList)
            shoppingListAdapter.notifyDataSetChanged()
        }
    }

}