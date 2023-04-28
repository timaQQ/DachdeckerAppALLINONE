package de.timurg.dachdeckerappallinone.presentation.adapter.formcollection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.domain.model.FFlaechenItem
import de.timurg.dachdeckerappallinone.presentation.ui.formcollection.FormCollectionFragmentDirections

class FFlaechenAdapter(val context: Context, var dataset: List<FFlaechenItem>):
    RecyclerView.Adapter<FFlaechenAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var listCard = view.findViewById<CardView>(R.id.singleline_listitem_card)
        var itemTitle = view.findViewById<TextView>(R.id.singleline_listitem_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.singleline_listitem, parent, false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        with(holder){
            itemTitle.text = item.itemTitle
            listCard.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(FormCollectionFragmentDirections
                        .actionFormCollectionFragmentToFormCollectionItem(item))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}