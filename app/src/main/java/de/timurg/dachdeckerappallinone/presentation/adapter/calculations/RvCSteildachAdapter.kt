package de.timurg.dachdeckerappallinone.presentation.adapter.calculations

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.domain.model.CListItem
import de.timurg.dachdeckerappallinone.presentation.ui.calculations.CalculationsFragmentDirections

class RvCSteildachAdapter(val context: Context, var dataset: List<CListItem>):
    RecyclerView.Adapter<RvCSteildachAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var listCard = view.findViewById<CardView>(R.id.singleline_listitem_card)
        var itemTitle = view.findViewById<TextView>(R.id.singleline_listitem_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.singleline_listitem, parent, false)
        return ItemViewHolder(itemLayout)
    }

    fun filter(list: List<CListItem>, keywordOne: String, keywordTwo: String){
        dataset = list.filter {
            it.categoryOne.contains(keywordOne, true)
            it.categoryTwo.contains(keywordTwo, true) }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        if (holder.itemTitle.text == "Konterlattung") {
            holder.listCard.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(
                        CalculationsFragmentDirections
                            .actionCalculationsFragmentToCalculationsDetailFragment()
                    )
            }
        } else {
            with(holder) {
                itemTitle.text = item.itemName
                listCard.setOnClickListener {
                    holder.itemView.findNavController()
                        .navigate(
                            CalculationsFragmentDirections
                                .actionCalculationsFragmentToCalculationsMaterialbedarfFlaechenziegelFragment()
                        )
                }
            }
        }



    }



    override fun getItemCount(): Int {
        return dataset.size
    }

}