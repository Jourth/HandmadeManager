package ru.juxlab.handmademanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hm_folders")
data class Folder (
    @PrimaryKey(autoGenerate = true)
    var folderId: Int = 0
)