package de.timurg.dachdeckerappallinone.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Project(
    @DocumentId
    val id: String = "",
    var title: String = "",
    var subTitle: String = "",
    var isChecked: Boolean = false,
    @ServerTimestamp
    val timestamp: Date = Date()
): Parcelable
