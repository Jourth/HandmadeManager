package ru.juxlab.handmademanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.juxlab.handmademanager.data.model.Folder
import ru.juxlab.handmademanager.data.model.HandmadeObject
import ru.juxlab.handmademanager.data.model.HandmadeObjectImage
import ru.juxlab.handmademanager.data.model.HandmadeObjectProperty
import ru.juxlab.handmademanager.data.model.PropertyMetadata
import ru.juxlab.handmademanager.data.model.PropertyType

@Database(
    version = 1,

    entities = [
        HandmadeObject::class,
        HandmadeObjectImage::class,
        HandmadeObjectProperty::class,
        Folder::class,
        PropertyMetadata::class
               ]

)
@TypeConverters(Converters::class)
abstract class HandmadeManagerDatabase : RoomDatabase() {

    abstract fun handmadeManagerDao(): HandmadeManagerDao

    companion object{
        @Volatile private var instance: HandmadeManagerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also{ instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, HandmadeManagerDatabase::class.java, "hmm.db").build()
    }
}