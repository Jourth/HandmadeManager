package ru.juxlab.handmademanager.ui.edit_hmobject

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ru.juxlab.handmademanager.R
import ru.juxlab.handmademanager.data.model.HandmadeObject

class HandmadeObjectActivity: AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HandmadeObjectViewModelFactory by instance()

    private lateinit var viewModel: HandmadeObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HandmadeObjectViewModel::class.java)


        bindUI()
    }

    private fun bindUI(){
        setContentView(R.layout.activity_handmade_object)

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            saveHandmadeObject()
            this@HandmadeObjectActivity.finish() }
    }

    private fun saveHandmadeObject() {

        val editTextName        = findViewById<EditText>(R.id.editText_object_name)
        val editTextDescription = findViewById<EditText>(R.id.editText_object_description)

        val name        = editTextName.text.toString()
        val description = editTextDescription.text.toString()


        val handmadeObject = HandmadeObject(
            0,
            0,
            name,
            description
            )

        viewModel.addHandmadeObject(handmadeObject)
    }

}