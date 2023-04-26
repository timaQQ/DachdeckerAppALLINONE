package de.timurg.dachdeckerappallinone.presentation.adapter.projects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.domain.model.Calculation

class RvProjectsCalculationsAdapter(val context: Context, var dataset: MutableList<Calculation>):
    RecyclerView.Adapter<RvProjectsCalculationsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var listCard = view.findViewById<CardView>(R.id.singleline_listitem_card)
        var itemTitle = view.findViewById<TextView>(R.id.singleline_listitem_text)
    }

    fun submitList(list: MutableList<Calculation>){
        dataset = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.multiline_listitem, parent, false)
        return ItemViewHolder(itemLayout)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]


        with(holder){

            itemTitle.text = item.product

            listCard.setOnClickListener {
                holder.itemView.findNavController()
            }

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}