package de.timurg.dachdeckerappallinone.presentation.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.appbar.MaterialToolbar
import de.timurg.dachdeckerappallinone.databinding.FragmentCalculationsProjectBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.projects.RvProjectsCalculationsAdapter
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel

class ProjectsCalculationsFragment: Fragment() {
    private var _binding: FragmentCalculationsProjectBinding? = null
    private val binding: FragmentCalculationsProjectBinding
        get() = _binding ?: throw RuntimeException("FragmentCalculationsProjectBinding == null")
    private lateinit var toolbar: MaterialToolbar
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculationsProjectBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewmodel.getCalculationsFromProject()
        val itemList = viewModel.calculationsListFP
        val cAdapter = RvProjectsCalculationsAdapter(requireContext(), itemList.value!!)


        with(binding){

            calculationsProjectAdapter.adapter = cAdapter


        }
    }
}