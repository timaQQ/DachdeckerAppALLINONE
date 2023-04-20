package de.timurg.dachdeckerappallinone.domain.model

import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId
    val id: String = "",
    val name: String = ""
)
