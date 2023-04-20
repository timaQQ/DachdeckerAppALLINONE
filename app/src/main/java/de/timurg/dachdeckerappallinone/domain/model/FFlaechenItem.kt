package de.timurg.dachdeckerappallinone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FFlaechenItem(
    var itemTitle: String,
    var itemImage: Int,
    var formula: Int,
    var description: String
): Parcelable
