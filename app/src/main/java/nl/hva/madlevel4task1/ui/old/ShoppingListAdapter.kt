package nl.hva.madlevel4task1.ui.old

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import nl.hva.madlevel4task1.R
import nl.hva.madlevel4task1.databinding.ItemProductBinding
import nl.hva.madlevel4task1.model.Product

class ShoppingListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemProductBinding.bind(itemView)

        fun databind(product: Product) {
            binding.resultText.text = product.resultText;
            
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(products[position])
    }
}