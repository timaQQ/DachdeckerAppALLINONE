package de.timurg.dachdeckerappallinone.presentation.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import de.timurg.dachdeckerappallinone.databinding.FragmentProjectsBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.projects.RvProjectsAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding: FragmentProjectsBinding
        get() = _binding ?: throw RuntimeException("FragmentProjectsBinding == null")
    private lateinit var toolbar: MaterialToolbar
    private val viewmodel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        toolbar = binding.projectsToolbar

        viewmodel.getProjects()

        viewmodel.projectsList.observe(viewLifecycleOwner){
            binding.projectsRecycler.adapter = RvProjectsAdapter(requireContext(), it)
        }



        with(binding) {




            addNewprojectButton.setOnClickListener {
                findNavController().navigate(
                    ProjectsFragmentDirections.actionProjectsFragmentToNewprojectFragment()
                )
            }
            projectsSortButton.setOnClickListener {
                //TODO
            }
            projectsFilterButton.setOnClickListener {
                //TODO
            }
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