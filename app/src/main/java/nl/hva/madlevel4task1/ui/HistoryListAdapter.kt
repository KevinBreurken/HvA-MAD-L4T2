package nl.hva.madlevel4task1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import nl.hva.madlevel4task1.GameActionType
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

            fun getID(type: GameActionType): Int {
                if(type == GameActionType.ROCK)
                    return R.drawable.rock
                if(type == GameActionType.PAPER)
                    return R.drawable.paper
                if(type == GameActionType.SCISSOR)
                    return R.drawable.scissor
                return -1
            }

            binding.computerHistoryImageView.setImageResource(getID(history.computerAction))
            binding.playerHistoryImageView.setImageResource(getID(history.playerAction))
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