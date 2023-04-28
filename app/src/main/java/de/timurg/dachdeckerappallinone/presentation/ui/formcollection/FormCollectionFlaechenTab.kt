package de.timurg.dachdeckerappallinone.presentation.ui.formcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.timurg.dachdeckerappallinone.data.FormCollectionData
import de.timurg.dachdeckerappallinone.databinding.TabFlaechenFormcollectionBinding
import de.timurg.dachdeckerappallinone.presentation.adapter.formcollection.FFlaechenAdapter

class FormCollectionFlaechenTab : Fragment() {
    private var _binding: TabFlaechenFormcollectionBinding? = null
    private val binding: TabFlaechenFormcollectionBinding
        get() = _binding ?: throw RuntimeException("TabFlaechenFormcollectionBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TabFlaechenFormcollectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val itemList = FormCollectionRepository().loadFlaechenFCTab()
        val itemList = FormCollectionData().formcollectionList

        binding.flaechenRecycler.adapter = FFlaechenAdapter(requireContext(), itemList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}