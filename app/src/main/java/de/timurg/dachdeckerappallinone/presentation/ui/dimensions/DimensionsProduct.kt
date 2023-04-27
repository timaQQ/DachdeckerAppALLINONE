package de.timurg.dachdeckerappallinone.presentation.ui.dimensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.appbar.MaterialToolbar
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.ProductDimensionsBinding
import de.timurg.dachdeckerappallinone.domain.model.ProductItem
import de.timurg.dachdeckerappallinone.presentation.ui.DimensionsViewModel

class DimensionsProduct : Fragment() {

    private var _binding: ProductDimensionsBinding? = null
    private val binding: ProductDimensionsBinding
        get() = _binding ?: throw RuntimeException("ProductDimensionsBinding == null")

    private val viewModel: DimensionsViewModel by viewModels()

    private lateinit var toolbar: MaterialToolbar
    private lateinit var productImage: ImageView
//    private lateinit var

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDimensionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = requireArguments().getParcelable<ProductItem>("product")

        toolbar = binding.productToolbar


        with(binding) {

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            if (product != null) {


                toolbar.title = product.productName
                setWidthText.text = product.setWidth
                setLengthText.text = product.setLength
                requiredText.text = product.requiredSqm
                weightText.text = product.weight
                roofPitchText.text = product.roofPitch
                productImage.load(product.productImage){
                    placeholder(R.drawable.ic_round_image)
                    error(R.drawable.ic_round_image_not_supported)
                }
                dimensionsImage.load(product.dimensionsImage){
                    placeholder(R.drawable.ic_round_image)
                    error(R.drawable.ic_round_image_not_supported)
                }
                eavesImage.load(product.eavesImage){
                    placeholder(R.drawable.ic_round_image)
                    error(R.drawable.ic_round_image_not_supported)
                }
                vergeImage.load(product.dimensionsImage){
                    placeholder(R.drawable.ic_round_image)
                    error(R.drawable.ic_round_image_not_supported)
                }

            }


        }


    }

}