package de.timurg.dachdeckerappallinone.presentation.ui.calculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.timurg.dachdeckerappallinone.databinding.FragmentEinteilungFassadeCalculationsBinding

class CalculationsFassadeEinteilungFragment : Fragment() {

    private var _binding: FragmentEinteilungFassadeCalculationsBinding? = null
    private val binding: FragmentEinteilungFassadeCalculationsBinding
        get() = _binding ?: throw RuntimeException("FragmentEinteilungFassadeCalculationsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEinteilungFassadeCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}