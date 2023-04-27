package de.timurg.dachdeckerappallinone.presentation.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.timurg.dachdeckerappallinone.data.ProductsData
import de.timurg.dachdeckerappallinone.data.remote.ProductApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class DimensionsViewModel : ViewModel() {

    val repository = ProductsData(ProductApi)

    val products = repository.products

    fun loadProducts() {
        viewModelScope.launch {
            try {
                repository.getProduct()
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "failed loading products: $e")
            }
        }
    }
}