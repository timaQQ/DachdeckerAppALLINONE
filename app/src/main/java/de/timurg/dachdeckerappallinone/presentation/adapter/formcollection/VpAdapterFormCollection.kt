package de.timurg.dachdeckerappallinone.presentation.adapter.formcollection

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapterFormCollection(
    fragment: Fragment, private val tabs: List<Tab>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = tabs.size
    override fun createFragment(position: Int) = tabs[position].fragment
}

class Tab(val title: String, val fragment: Fragment)