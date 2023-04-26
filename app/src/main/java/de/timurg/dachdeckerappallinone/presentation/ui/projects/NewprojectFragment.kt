package de.timurg.dachdeckerappallinone.presentation.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.databinding.FragmentNewprojectBinding
import de.timurg.dachdeckerappallinone.domain.model.Project
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class NewprojectFragment : Fragment() {

    private var _binding: FragmentNewprojectBinding? = null
    private val binding: FragmentNewprojectBinding
        get() = _binding ?: throw RuntimeException("FragmentNewprojectBinding == null")

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewprojectBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            newprojectToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            buttonCreateNewproject.setOnClickListener {
                var tempProject = Project(
                    title = projectNameEdittext.text.toString(),
                    subTitle = projectSubnameEdittext.text.toString()
                )
                viewModel.createProject(tempProject, )
                findNavController()
                    .navigateUp()
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}