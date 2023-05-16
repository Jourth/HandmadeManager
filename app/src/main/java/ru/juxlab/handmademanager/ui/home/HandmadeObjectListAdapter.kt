package ru.juxlab.handmademanager.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.juxlab.handmademanager.R
import ru.juxlab.handmademanager.data.model.HandmadeObject
import ru.juxlab.handmademanager.ui.edit_hmobject.HandmadeObjectActivity

class HandmadeObjectListAdapter(val viewModel: HomeViewModel): RecyclerView.Adapter<HandmadeObjectListAdapter.HandmadeObjectListViewHolder>() {

    private var handmadeObjectList = emptyList<HandmadeObject>()

    class HandmadeObjectListViewHolder(val cardView: CardView, val viewModel: HomeViewModel): RecyclerView.ViewHolder(cardView){
        private val nameView       = cardView.findViewById<TextView>(R.id.textView_list_object_name)
        private val descriptonView = cardView.findViewById<TextView>(R.id.textView_list_object_description)
        private val objectImageView = cardView.findViewById<ImageView>(R.id.imageView_list_object_image)
        private val rowView        = cardView



        fun bind(handmadeObject: HandmadeObject){
            nameView.text = handmadeObject.objectName
            descriptonView.text = handmadeObject.objectDescription

            viewModel.readHandmadeObjectImage(handmadeObject.imageId, objectImageView)

            rowView.setOnClickListener {
                val handmadeObjectIntent = Intent(it.context, HandmadeObjectActivity::class.java)
                handmadeObjectIntent.putExtra(HandmadeObjectActivity.ID_KEY, handmadeObject.objectId)
                it.context.startActivity(handmadeObjectIntent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandmadeObjectListViewHolder {
        return HandmadeObjectListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_list_row, parent, false) as CardView, viewModel)
    }

    override fun getItemCount(): Int {
        return handmadeObjectList.size
    }

    override fun onBindViewHolder(holder: HandmadeObjectListViewHolder, position: Int) {
        val handmadeObject = handmadeObjectList[position]
        holder.bind(handmadeObject)
    }

    fun setData(currentHandmadeObjectList: List<HandmadeObject>){
        handmadeObjectList = currentHandmadeObjectList
        notifyDataSetChanged()
    }

}