package de.timurg.dachdeckerappallinone.presentation.adapter.dimensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.domain.model.ProductItem
import de.timurg.dachdeckerappallinone.presentation.ui.dimensions.DimensionsCollectionFragmentDirections

class RvProductAdapter : RecyclerView.Adapter<RvProductAdapter.ItemViewHolder>() {

    private var dataset = mutableListOf<ProductItem>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var listCard = view.findViewById<CardView>(R.id.singleline_listitem_card)
        var productName = view.findViewById<TextView>(R.id.singleline_listitem_text)
    }

    fun submitList(list: MutableList<ProductItem>){
        dataset = list
        notifyDataSetChanged()
    }

    fun filter(list: MutableList<ProductItem>, keyword: String){
        dataset = list.filter {
            it.manufacturer.contains(keyword, true) } as MutableList<ProductItem>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.singleline_listitem, parent, false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = dataset[position]

        with(holder){
            productName.text = product.productName
            listCard.setOnClickListener {
                holder.itemView.findNavController()
                    .navigate(
                        DimensionsCollectionFragmentDirections
                            .actionDimensionsCollectionFragmentToDimensionsProduct(product)
                    )

            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}