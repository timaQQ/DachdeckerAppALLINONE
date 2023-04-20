package de.timurg.dachdeckerappallinone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val dimensionsImage: String,
    val eavesImage: String,
    val id: Int,
    val manufacturer: String,
    val productImage: String,
    val productName: String,
    val requiredSqm: String,
    val ridgeImage: String,
    val roofPitch: String,
    val setLength: String,
    val setWidth: String,
    val vergeImage: String,
    val weight: String
): Parcelable