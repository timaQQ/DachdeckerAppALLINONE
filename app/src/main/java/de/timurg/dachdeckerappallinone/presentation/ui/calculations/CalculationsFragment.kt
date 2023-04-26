package de.timurg.dachdeckerappallinone.presentation.ui.calculations

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
import de.timurg.dachdeckerappallinone.databinding.FragmentCalculationsBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.calculations.Tab
import de.timurg.dachdeckerappallinone.presentation.adapter.calculations.VpAdapterCalculations

class CalculationsFragment : Fragment() {

    private var _binding: FragmentCalculationsBinding? = null
    private val binding: FragmentCalculationsBinding
        get() = _binding ?: throw RuntimeException("FragmentCalculationsBinding == null")

    private lateinit var pager: ViewPager2
    private lateinit var tab: TabLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tabs = listOf(
            Tab("Fassade", CalculationsFassadeTab()),
            Tab("Steildach", CalculationsSteildachTab()),
            Tab("Flachdach", CalculationsFlachdachTab())
        )
        val adapter = VpAdapterCalculations(this, tabs)

        pager = binding.viewpagerCalculations
        pager.adapter = adapter
        tab = binding.calculationsTabLayout
        toolbar = binding.calculationsToolbar

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