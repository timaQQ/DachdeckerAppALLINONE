package de.timurg.dachdeckerappallinone.presentation.ui.formcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.timurg.dachdeckerappallinone.databinding.FragmentFormCollectionBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.formcollection.Tab
import de.timurg.dachdeckerappallinone.presentation.adapter.formcollection.VpAdapterFormCollection

class FormCollectionFragment : Fragment() {

    private var _binding: FragmentFormCollectionBinding? = null
    private val binding: FragmentFormCollectionBinding
        get() = _binding ?: throw RuntimeException("FragmentFormCollectionBinding == null")

    private lateinit var pager: ViewPager2
    private lateinit var tab: TabLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabs = listOf(
            Tab("Flächen", FormCollectionFlaechenTab()),

            //TODO : Future
//            Tab("Trigonometrie", FormCollectionTrigonometrieTab()),
//            Tab("Dachaufbauten", FormCollectionDachaufbautenTab()),
//            Tab("Dachgeometrie", FormCollectionDachgeometrieTab())
        )
        val adapter = VpAdapterFormCollection(this, tabs)

        pager = binding.viewpagerFormcollection
        pager.adapter = adapter
        tab = binding.formcollectionTabLayout
        toolbar = binding.formcollectionToolbar

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