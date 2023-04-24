package ru.juxlab.handmademanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_properties_metadata")
class PropertyMetadata {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}