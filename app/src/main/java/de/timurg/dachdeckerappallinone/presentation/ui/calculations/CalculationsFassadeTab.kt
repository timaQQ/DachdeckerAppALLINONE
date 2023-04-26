package de.timurg.dachdeckerappallinone.presentation.ui.calculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.TabFassadeCalculationsBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.calculations.RvCDetailAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class CalculationsFassadeTab : Fragment() {
    private var _binding: TabFassadeCalculationsBinding? = null
    private val binding: TabFassadeCalculationsBinding
        get() = _binding ?: throw RuntimeException("TabFassadeCalculationsBinding == null")
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabFassadeCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = viewModel.calcItemsList
        val cAdapter = RvCDetailAdapter(requireContext(), itemList)

        val fassadeCalcArray = resources.getStringArray(R.array.calculations_fassade_dropdown)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.calculations_dropdown_item, fassadeCalcArray)


        viewModel.loadProducts()

        viewModel.calcItems.observe(viewLifecycleOwner){
            cAdapter.filter(it, "Fassade", "Einteilungen")
        }


        with(binding){
            actvDropdown.setAdapter(arrayAdapter)
            fassadeRecycler.isVisible = false
            fassadeRecycler.adapter = cAdapter
            actvDropdown.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val selectedItem = fassadeCalcArray[position]
                    when (position) {
                        0 -> fassadeRecycler.isVisible = true
                        1 -> fassadeRecycler.isVisible = false
                        2 -> fassadeRecycler.isVisible = false
                    }
                }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}