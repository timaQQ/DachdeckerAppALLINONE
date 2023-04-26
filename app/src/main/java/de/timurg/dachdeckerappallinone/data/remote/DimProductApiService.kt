package de.timurg.dachdeckerappallinone.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.timurg.dachdeckerappallinone.domain.model.ProductItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DimProductApiService {

    @GET("products.json")
    suspend fun getProduct(): MutableList<ProductItem>

}

const val BASE_URL = "https://public.syntax-institut.de/apps/batch4/Timur/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



object ProductApi {
    val retroservice: DimProductApiService by lazy {
        retrofit.create(DimProductApiService::class.java)
    }
}