package de.timurg.dachdeckerappallinone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.timurg.dachdeckerappallinone.data.remote.ProductApi
import de.timurg.dachdeckerappallinone.domain.model.ProductItem

class ProductsData(private val api: ProductApi) {

    private val _products = MutableLiveData<MutableList<ProductItem>>()
    val products: LiveData<MutableList<ProductItem>>
        get() = _products

    suspend fun getProduct() {
        _products.value = api.retroservice.getProduct()
    }
}