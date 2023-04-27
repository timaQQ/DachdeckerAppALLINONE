package de.timurg.dachdeckerappallinone.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            currentUser.observe(viewLifecycleOwner) {
                if (it == null) {
                    findNavController().navigate(R.id.loginFragment)
                } else {
                    viewModel.getUserData()
                }
            }
            user.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.tvWelcomeMain.text = "Willkommen ${it.name}"
                }
            }
        }

        with(binding) {

            mainToolbar.setNavigationOnClickListener {
                viewModel.logOut()
            }

            buttonFormcollection.setOnClickListener {
                //TODO:
                TODO()

            }
            buttonMyProjects.setOnClickListener {
                findNavController()
                    .navigate(
                        MainFragmentDirections
                            .actionMainFragmentToProjectsFragment()
                    )
            }
            buttonCalculations.setOnClickListener {
                findNavController()
                    .navigate(
                        MainFragmentDirections
                            .actionMainFragmentToCalculationsFragment()
                    )
            }
            buttonDimensions.setOnClickListener {
                findNavController()
                    .navigate(
                        MainFragmentDirections
                            .actionMainFragmentToDimensionsCollectionFragment()
                    )
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}