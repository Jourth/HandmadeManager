package ru.juxlab.handmademanager.ui.edit_hmobject

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ru.juxlab.handmademanager.R
import ru.juxlab.handmademanager.data.model.HandmadeObject
import ru.juxlab.handmademanager.data.model.HandmadeObjectImage

class HandmadeObjectActivity: AppCompatActivity(), KodeinAware {

    companion object {
        const val ID_KEY = "ID"
        const val PICK_IMAGE = 100
    }

    override val kodein by closestKodein()
    private val viewModelFactory: HandmadeObjectViewModelFactory by instance()
    var newImage = false

    private lateinit var viewModel: HandmadeObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HandmadeObjectViewModel::class.java)

        bindUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.need_read_storage_permission, Toast.LENGTH_LONG).show()
            } else{
                val selectedImage = data?.data
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                val objectImage = findViewById<ImageView>(R.id.imageView_object_image)
                objectImage.setImageBitmap(bitmap)

                newImage = true}

        }
    }

    private fun bindUI(){
        setContentView(R.layout.activity_handmade_object)

        val saveButton = findViewById<Button>(R.id.button_save)
        saveButton.setOnClickListener {
            saveHandmadeObject()
            this@HandmadeObjectActivity.finish()
        }

        val imagePickButton = findViewById<Button>(R.id.button_image_pick)
        imagePickButton.setOnClickListener{
            intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        val objectId = intent.getIntExtra(ID_KEY, 0)
        if (objectId > 0){
            val handmadeObjectLiveData = viewModel.readHandmadeObject(intent.getIntExtra(ID_KEY, 0))
            handmadeObjectLiveData.observe(this) {
                val editTextName = findViewById<EditText>(R.id.editText_object_name)
                val editTextDescription = findViewById<EditText>(R.id.editText_object_description)
                editTextName.setText(it.objectName)
                editTextDescription.setText(it.objectDescription)
                viewModel.currentHandmadeObject = it

                if (it.imageId > 0) {

                    val imageViewObjectImage =
                        findViewById<ImageView>(R.id.imageView_object_image)
                    viewModel.readHandmadeObjectImage(it.imageId, imageViewObjectImage)

                }

            }
        }

    }

    private fun saveHandmadeObject() {

        val editTextName         = findViewById<EditText>(R.id.editText_object_name)
        val editTextDescription  = findViewById<EditText>(R.id.editText_object_description)
        val imageViewObjectImage = findViewById<ImageView>(R.id.imageView_object_image)

        val name        = editTextName.text.toString()
        val description = editTextDescription.text.toString()
        val bitmap      = (imageViewObjectImage.drawable as BitmapDrawable).bitmap

        val handmadeObjectImage = HandmadeObjectImage(
            0,
            bitmap
        )

        val handmadeObject = HandmadeObject(
            0,
            0,
            name,
            description
            )

        viewModel.saveHandmadeObject(handmadeObject, handmadeObjectImage, newImage)
    }

}