package de.timurg.dachdeckerappallinone.presentation.ui.formcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import de.timurg.dachdeckerappallinone.databinding.ItemFormcollectionBinding
import de.timurg.dachdeckerappallinone.domain.model.FFlaechenItem

class FormCollectionItem : Fragment() {

    private var _binding: ItemFormcollectionBinding? = null
    private val binding: ItemFormcollectionBinding
        get() = _binding ?: throw RuntimeException("ItemFormcollectionBinding == null")

    private lateinit var toolbar: MaterialToolbar
    private lateinit var scrollView: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemFormcollectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = binding.formcollectionItemToolbar

        val item = requireArguments().getParcelable<FFlaechenItem>("item")
        if (item != null) {
            with(binding) {
                formcollectionItemDescription.text = item.description
                formcollectionItemToolbar.title = item.itemTitle
                formcollectionItemImage.setImageResource(item.itemImage)
                formcollectionItemFormula.setImageResource(item.formula)

                toolbar.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}