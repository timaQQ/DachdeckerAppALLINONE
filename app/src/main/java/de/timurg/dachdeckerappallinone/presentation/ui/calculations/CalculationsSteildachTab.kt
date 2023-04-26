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
import de.timurg.dachdeckerappallinone.databinding.TabSteildachCalculationsBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.calculations.RvCSteildachAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class CalculationsSteildachTab: Fragment() {

    private var _binding: TabSteildachCalculationsBinding? = null
    private val binding: TabSteildachCalculationsBinding
        get() = _binding ?: throw RuntimeException("TabSteildachCalculationsBinding == null")
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabSteildachCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = viewModel.calcItemsList
        val cAdapter = RvCSteildachAdapter(requireContext(), itemList)

        val steildachCalcArray = resources.getStringArray(R.array.calculations_fassade_dropdown)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.calculations_dropdown_item, steildachCalcArray)


        viewModel.loadProducts()

        viewModel.calcItems.observe(viewLifecycleOwner){
            cAdapter.filter(it, "Steildach", "Materialbedarf")
        }


        with(binding){
            actvDropdown.setAdapter(arrayAdapter)
            steildachRecycler.isVisible = false
            steildachRecycler.adapter = cAdapter
            actvDropdown.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val selectedItem = steildachCalcArray[position]
                    when (position) {
                        0 -> steildachRecycler.isVisible = false
                        1 -> steildachRecycler.isVisible = true
                        2 -> steildachRecycler.isVisible = false
                    }
                }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}