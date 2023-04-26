package de.timurg.dachdeckerappallinone.presentation.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.databinding.FragmentProjectBinding
import de.timurg.dachdeckerappallinone.domain.model.Project
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class ProjectFragment: Fragment() {

    private var _binding: FragmentProjectBinding? = null
    private val binding: FragmentProjectBinding
        get() = _binding ?: throw RuntimeException("FragmentProjectBinding == null")

    private val viewmodel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val project = requireArguments().getParcelable<Project>("project")

        with(binding){
            projectToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            if (project != null) {
                projectNameEdittext.setText(project.title)
                projectSubnameEdittext.setText(project.subTitle)
            }

            buttonDelete.setOnClickListener {
                viewmodel.deleteProject(project!!.id)
                findNavController()
                    .navigate(ProjectFragmentDirections.actionProjectFragmentToMainFragment())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}