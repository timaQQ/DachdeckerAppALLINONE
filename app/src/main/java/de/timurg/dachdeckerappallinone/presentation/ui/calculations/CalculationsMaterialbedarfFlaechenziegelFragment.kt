package de.timurg.dachdeckerappallinone.presentation.ui.calculations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.switchmaterial.SwitchMaterial
import de.timurg.dachdeckerappallinone.R
import de.timurg.dachdeckerappallinone.databinding.FragmentFlaechenziegelMaterialbedarfCalculationsBinding
import de.timurg.dachdeckerappallinone.domain.model.Calculation
import de.timurg.dachdeckerappallinone.presentation.ui.MainViewModel
import kotlin.math.roundToInt

class CalculationsMaterialbedarfFlaechenziegelFragment : Fragment() {

    private var _binding: FragmentFlaechenziegelMaterialbedarfCalculationsBinding? = null
    private val binding: FragmentFlaechenziegelMaterialbedarfCalculationsBinding
        get() = _binding ?: throw RuntimeException(
            "FragmentFlaechenziegelMaterialbedarfCalculationsBinding == null"
        )
    private val viewModel: MainViewModel by activityViewModels()
    //    private lateinit var


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlaechenziegelMaterialbedarfCalculationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var switch = binding.switchRWrLDouble
        var isChecked = false

        val dropdownArrayManufacturer = resources
            .getStringArray(R.array.calculations_material_flaechenziegel_manufacturer_dropdown)
        val dropdownArrayProductBraas = resources
            .getStringArray(R.array.calculations_material_flaechenziegel_product_braas_dropdown)
        val dropdownArrayProductCreaton = resources
            .getStringArray(R.array.calculations_material_flaechenziegel_product_creaton_dropdown)
        val dropdownArrayWindows = resources
            .getStringArray(R.array.calculations_material_flaechenziegel_windows_dropdown)

        var chosenProduct: String? = null

        viewModel.loadProductsApi()

        with(binding) {

            showManufacturer()

            flaechentiegelToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            val manufacturerArrayAdapter = ArrayAdapter(
                requireContext(),
                R.layout.calculations_dropdown_item, dropdownArrayManufacturer
            )
            actvDropdownManufacturer.setAdapter(manufacturerArrayAdapter)
            actvDropdownManufacturer.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val selectedManufacturer = dropdownArrayManufacturer[position]

                    showProducts()

                    val productsArrayAdapter = when (selectedManufacturer) {
                        "Braas" -> ArrayAdapter(
                            requireContext(),
                            R.layout.calculations_dropdown_item, dropdownArrayProductBraas
                        )
                        "Creaton" -> ArrayAdapter(
                            requireContext(),
                            R.layout.calculations_dropdown_item, dropdownArrayProductCreaton
                        )
                        else -> throw IllegalStateException("Unknown Manufacturer")
                    }
                    ivProduct.isGone = true
                    actvDropdownProduct.setAdapter(productsArrayAdapter)

                    actvDropdownProduct.onItemClickListener =
                        AdapterView.OnItemClickListener { parent, _, position, _ ->
                            chosenProduct = when (selectedManufacturer) {
                                "Braas" -> dropdownArrayProductBraas[position]
                                "Creaton" -> dropdownArrayProductCreaton[position]
                                else -> throw IllegalStateException("Unknown chosen product")
                            }
                            val productImage = when (chosenProduct) {
                                getString(R.string.braasProductR9) ->
                                    viewModel.products.value?.get(0)?.productImage
                                getString(R.string.braasProductR13) ->
                                    viewModel.products.value?.get(1)?.productImage
                                getString(R.string.braasProductA12) ->
                                    viewModel.products.value?.get(2)?.productImage
                                getString(R.string.braasProductO) ->
                                    viewModel.products.value?.get(3)?.productImage
                                getString(R.string.braasProductFP) ->
                                    viewModel.products.value?.get(4)?.productImage
                                getString(R.string.braasProductDSP) ->
                                    viewModel.products.value?.get(5)?.productImage
                                getString(R.string.braasProductHP) ->
                                    viewModel.products.value?.get(6)?.productImage
                                getString(R.string.creatonProductTi) ->
                                    viewModel.products.value?.get(7)?.productImage
                                getString(R.string.creatonProductFu) ->
                                    viewModel.products.value?.get(8)?.productImage
                                getString(R.string.creatonProductMa) ->
                                    viewModel.products.value?.get(9)?.productImage
                                getString(R.string.creatonProductPr) ->
                                    viewModel.products.value?.get(10)?.productImage
                                getString(R.string.creatonProductMZ3) ->
                                    viewModel.products.value?.get(11)?.productImage
                                else -> throw IllegalStateException("Unknown Product")

                            }
                            if (productImage != null) {
                                showScreen(productImage)
                            }

                        }
                }
            val windowsArrayAdapter = ArrayAdapter(
                requireContext(),
                R.layout.calculations_dropdown_item, dropdownArrayWindows
            )
            actvDropdownWindows.setAdapter(windowsArrayAdapter)

            tvResult.setOnClickListener {
                var rLength = editTextRLength.text.toString().toDouble()
                var rWidth = editTextRWidth.text.toString().toDouble()
                var windowModel = actvDropdownWindows.text.toString()
                var windowsCount = editTextNWindows.text.toString().toInt()
                var windowModelDim = when (windowModel) {
                    getString(R.string.winGVK) -> 0.41 * 0.56
                    getString(R.string.winCK04) -> 0.55 * 1.18
                    getString(R.string.winCK06) -> 0.66 * 1.40
                    getString(R.string.winFK06) -> 0.66 * 1.40
                    getString(R.string.winFK08) -> 0.66 * 1.40
                    getString(R.string.winMK06) -> 0.78 * 1.18
                    getString(R.string.winMK08) -> 0.78 * 1.40
                    getString(R.string.winMK10) -> 0.78 * 1.60
                    getString(R.string.winPK06) -> 0.94 * 1.18
                    getString(R.string.winPK08) -> 0.94 * 1.40
                    getString(R.string.winPK10) -> 0.94 * 1.60
                    getString(R.string.winSK06) -> 1.14 * 1.18
                    getString(R.string.winSK08) -> 1.14 * 1.40
                    getString(R.string.winSK10) -> 1.14 * 1.60
                    getString(R.string.winUK08) -> 1.34 * 1.40
                    else -> throw IllegalStateException("Unknown chosen windowmodel for dimensions")
                }

                var result = when (chosenProduct) {
                    getString(R.string.braasProductR9) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 10.2
                    getString(R.string.braasProductR13) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 13.5
                    getString(R.string.braasProductA12) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 13.3
                    getString(R.string.braasProductO) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 33.8
                    getString(R.string.braasProductFP) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 13.1
                    getString(R.string.braasProductDSP) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 11.6
                    getString(R.string.braasProductHP) ->
                        calculate(rLength, rWidth, windowModelDim, windowsCount, switch) * 12.0
                    else -> throw IllegalStateException("Unknown chosen product")


                }
                tvResult.text = result.roundToInt().toString()
                fabAddCalulationToProject.isGone = false
                tvResultHint.isGone = false
                if (switch.isChecked()) {
                    isChecked = true
                    Log.d("CalcMaterialFlaechenziegelFragment", "isChecked = true")
                }
                if (!switch.isChecked()) {
                    isChecked = false
                    Log.d("CalcMaterialFlaechenziegelFragment", "isChecked = false")
                }

                fabAddCalulationToProject.setOnClickListener {
                    val calculation = Calculation(
                        "",
                        chosenProduct!!,
                        rLength.toString(),
                        rWidth.toString(),
                        isChecked,
                        windowModel,
                        windowsCount.toString(),
                        result.toString()
                    )
                    findNavController()
                        .navigate(
                            CalculationsMaterialbedarfFlaechenziegelFragmentDirections
                                .actionCalculationsMaterialbedarfFlaechenziegelFragmentToCalculationsUploadToprojectFragment(
                                    calculation
                                )
                        )
                }

            }

        }

    }

    private fun calculate(
        length: Double,
        width: Double,
        wModel: Double,
        wn: Int,
        switch: SwitchMaterial
    ): Double {
        var tempResult = if (switch.isChecked()) {
            ((length * width) - (wModel * wn)) * 2
        } else {
            ((length * width) - (wModel * wn))
        }
        return tempResult
    }

    private fun showManufacturer() {
        with(binding) {
            tilCWindowsL.isGone = true
            tilCWindowsW.isGone = true
            ivProduct.isGone = true
            actvDropdownProduct.isGone = true
            tilDropdownProduct.isGone = true
            tilRLength.isGone = true
            tilRWidth.isGone = true
            actvDropdownWindows.isGone = true
            tilDropdownWindows.isGone = true
            tilNWindows.isGone = true
            tvResult.isGone = true
            tvReminder.isVisible = true
            fabAddCalulationToProject.isGone = true
            switchRWrLDouble.isGone = true
            tvResultHint.isGone = true
        }
    }
    private fun showProducts() {
        with(binding) {
            actvDropdownProduct.setText("")
            actvDropdownProduct.isEnabled = true
            actvDropdownProduct.isGone = false
            tilDropdownProduct.isGone = false
            tilRLength.isGone = true
            tilRWidth.isGone = true
            actvDropdownWindows.isGone = true
            tilDropdownWindows.isGone = true
            tilNWindows.isGone = true
            tvResult.isGone = true
            tvResultHint.isGone = true
            tvReminder.isVisible = true
            switchRWrLDouble.isGone = true
            tvReminder.text = "Bitte wähle ein Produkt"
        }
    }
    private fun showScreen(pI: String) {
        with(binding) {
            ivProduct.isVisible = true
            ivProduct.load(pI) {
                placeholder(R.drawable.ic_round_image)
                error(R.drawable.ic_round_image_not_supported)
            }

            tilRLength.isGone = false
            tilRWidth.isGone = false
            switchRWrLDouble.isGone = false
            actvDropdownWindows.isGone = false
            tilDropdownWindows.isGone = false
            tilNWindows.isGone = false
            tvResult.isGone = false
            tvReminder.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}