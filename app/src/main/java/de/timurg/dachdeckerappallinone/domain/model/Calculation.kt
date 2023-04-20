package de.timurg.dachdeckerappallinone.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Calculation(
    @DocumentId
    val id: String = "",
    val product: String = "",
    val roofLength: String = "",
    val roofWidth: String = "",
    val doubleValueisChecked: Boolean = false,
    val windowModel: String = "",
    val windowCount: String = "",
    val result: String = "",
    @ServerTimestamp
    val timestamp: Date = Date()
) : Parcelable