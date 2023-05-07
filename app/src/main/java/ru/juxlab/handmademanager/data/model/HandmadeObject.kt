package ru.juxlab.handmademanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_objects")
data class HandmadeObject(
    @PrimaryKey(autoGenerate = true)
    var objectId: Int = 0,

    var position: Int = 0,
    var objectName: String = "",
    var objectDescription: String = "",
)