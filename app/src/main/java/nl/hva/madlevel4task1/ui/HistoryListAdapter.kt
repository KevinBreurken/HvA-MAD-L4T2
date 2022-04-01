package nl.hva.madlevel4task1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.hva.madlevel4task1.R
import nl.hva.madlevel4task1.databinding.ItemProductBinding
import nl.hva.madlevel4task1.model.History

class HistoryListAdapter(private val histories: List<History>) :
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemProductBinding.bind(itemView)

        fun databind(history: History) {
            binding.resultText.text = history.result.toString();
            binding.dateText.text = history.createdDate.toString()
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
        return histories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(histories[position])
    }
}