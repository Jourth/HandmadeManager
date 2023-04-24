package ru.juxlab.handmademanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_object_properties")
class HandmadeObjectProperty(
    @PrimaryKey(autoGenerate = true)
    var propertyId: Int = 0
)
