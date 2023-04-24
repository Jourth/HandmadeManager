package ru.juxlab.handmademanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_objects")
data class HandmadeObject(
    @PrimaryKey(autoGenerate = true)
    var objectId: Int = 0

)