package de.timurg.dachdeckerappallinone.presentation.ui.calculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.databinding.FragmentToprojectUploadCalculationsBinding
import de.timurg.dachdeckerappallinone.domain.model.Calculation
import de.timurg.dachdeckerappallinone.presentation.adapter.calculations.RvCUploadToprojectAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class CalculationsUploadToprojectFragment: Fragment() {
    private var _binding: FragmentToprojectUploadCalculationsBinding? = null
    private val binding: FragmentToprojectUploadCalculationsBinding
        get() = _binding ?: throw RuntimeException(
            "FragmentToprojectUploadCalculationsBinding == null"
        )
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToprojectUploadCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            getProjects()
            val calculation = requireArguments().getParcelable<Calculation>("calculation")

            projectsList.observe(viewLifecycleOwner) {
                binding.projectsURecycler.adapter =
                    RvCUploadToprojectAdapter(requireContext(), it, viewModel, calculation!!)
            }
        }

        with(binding){

            uploadToprojectToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }



        }

    }
}