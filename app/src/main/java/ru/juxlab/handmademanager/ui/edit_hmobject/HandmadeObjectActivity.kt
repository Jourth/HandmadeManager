package ru.juxlab.handmademanager.ui.edit_hmobject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.juxlab.handmademanager.R

class HandmadeObjectActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        bindUI()
    }

    private fun bindUI(){
        setContentView(R.layout.activity_handmade_object)
    }
}