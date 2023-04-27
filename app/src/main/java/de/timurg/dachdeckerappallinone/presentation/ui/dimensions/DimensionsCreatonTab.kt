package de.timurg.dachdeckerappallinone.presentation.ui.dimensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.timurg.dachdeckerappallinone.databinding.TabCreatonDimensionsBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.dimensions.RvProductAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.DimensionsViewModel

class DimensionsCreatonTab : Fragment() {

    private var _binding: TabCreatonDimensionsBinding? = null
    private val binding: TabCreatonDimensionsBinding
        get() = _binding ?: throw RuntimeException("TabCreatonDimensionsBinding == null")
    private val viewModel: DimensionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabCreatonDimensionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dBAdapter = RvProductAdapter()
        binding.creatonRecycler.adapter = dBAdapter

        viewModel.loadProducts()

        viewModel.products.observe(viewLifecycleOwner) {
//            dBAdapter.submitList(it)
            dBAdapter.filter(it, "Creaton")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}