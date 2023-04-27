package de.timurg.dachdeckerappallinone.presentation.ui.dimensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.timurg.dachdeckerappallinone.databinding.FragmentDimensionsCollectionBinding
import de.timurg.dachdeckerappallinone.domain.model.ProductItem
import de.timurg.dachdeckerappallinone.presentation.adapter.dimensions.Tab
import de.timurg.dachdeckerappallinone.presentation.adapter.dimensions.VpAdapterDimensions

class DimensionsCollectionFragment : Fragment() {

    interface SearchResultListener {
        fun onSearchResult(result: List<ProductItem>)
    }

    private var _binding: FragmentDimensionsCollectionBinding? = null
    private val binding: FragmentDimensionsCollectionBinding
        get() = _binding ?: throw RuntimeException("FragmentDimensionsCollectionBinding == null")

    private lateinit var pager: ViewPager2
    private lateinit var tab: TabLayout
    private lateinit var toolbar: MaterialToolbar
    private lateinit var searchview: SearchView
//    private lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDimensionsCollectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabs = listOf(
            Tab("Braas", DimensionsBraasTab()),
            Tab("Creaton", DimensionsCreatonTab()),

            //TODO: Future
//            Tab("Erlus", DimensionsErlusTab()),
//            Tab("Jacobi", DimensionsJacobiTab()),
//            Tab("Laumans", DimensionsLaumansTab()),
//            Tab("Nelskamp", DimensionsNelskampTab()),
//            Tab("Roeben", DimensionsRoebenTab()),
//            Tab("Wienerberger", DimensionsWienerbergerTab()),

        )
        val adapter = VpAdapterDimensions(this, tabs)

        searchview = binding.dimensionsSearch
        pager = binding.viewpagerDimensions
        pager.adapter = adapter
        tab = binding.dimensionsTabLayout
        toolbar = binding.dimensionsToolbar

        TabLayoutMediator(tab, pager) { tab, position -> tab.text = tabs[position].title }.attach()

        with(binding) {

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}