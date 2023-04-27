package de.timurg.dachdeckerappallinone.presentation.ui.dimensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import de.timurg.dachdeckerappallinone.databinding.TabBraasDimensionsBinding
import de.timurg.dachdeckerappallinone.domain.model.ProductItem
import de.timurg.dachdeckerappallinone.presentation.adapter.dimensions.RvProductAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class DimensionsBraasTab() : Fragment(), DimensionsCollectionFragment.SearchResultListener {

    private var _binding: TabBraasDimensionsBinding? = null
    private val binding: TabBraasDimensionsBinding
        get() = _binding ?: throw RuntimeException("TabBraasDimensionsBinding == null")
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RvProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabBraasDimensionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dBAdapter = RvProductAdapter()
        binding.braasRecycler.adapter = dBAdapter

        viewModel.loadProducts()

        viewModel.products.observe(viewLifecycleOwner) {
//            dBAdapter.submitList(it)
            dBAdapter.filter(it, "Braas")
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchResult(result: List<ProductItem>) {
        TODO("Not yet implemented")
    }
}