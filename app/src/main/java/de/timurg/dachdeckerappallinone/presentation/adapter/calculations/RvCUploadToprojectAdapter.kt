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
import de.timurg.dachdeckerappallinone.domain.model.Calculation
import de.timurg.dachdeckerappallinone.domain.model.Project
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel
import de.timurg.dachdeckerappallinone.presentation.ui.calculations.CalculationsUploadToprojectFragmentDirections

class RvCUploadToprojectAdapter(val context: Context, var dataset: MutableList<Project>, var viewModel: MainViewModel, var calc: Calculation):
    RecyclerView.Adapter<RvCUploadToprojectAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var listCard = view.findViewById<CardView>(R.id.multiline_listitem_card)
        var itemTitle = view.findViewById<TextView>(R.id.multiline_name_text)
        var itemSubTitle = view.findViewById<TextView>(R.id.multiline_subtext_text)
//        var pinnedImage = view.findViewById<ImageView>(R.id.multiline_pinned_image)
//        var projectDoneImage = view.findViewById<ImageView>(R.id.multiline_checked_image)
    }

    fun submitList(list: MutableList<Project>){
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

            itemTitle.text = item.title
            itemSubTitle.text = item.subTitle

            listCard.setOnClickListener {
                viewModel.uploadCalculation(calc, item)
                holder.itemView.findNavController()
                    .navigate(CalculationsUploadToprojectFragmentDirections
                        .actionCalculationsUploadToprojectFragmentToCalculationsFragment())
            }

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}