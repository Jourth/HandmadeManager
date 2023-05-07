package ru.juxlab.handmademanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.juxlab.handmademanager.R
import ru.juxlab.handmademanager.data.model.HandmadeObject

class HandmadeObjectListAdapter(): RecyclerView.Adapter<HandmadeObjectListAdapter.HandmadeObjectListViewHolder>() {

    private var handmadeObjectList = emptyList<HandmadeObject>()

    class HandmadeObjectListViewHolder(cardView: CardView): RecyclerView.ViewHolder(cardView){
        private val nameView       = cardView.findViewById<TextView>(R.id.textView_list_object_name)
        private val descriptonView = cardView.findViewById<TextView>(R.id.textView_list_object_description)

        fun bind(handmadeObject: HandmadeObject){
            nameView.text = handmadeObject.objectName
            descriptonView.text = handmadeObject.objectDescription
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandmadeObjectListViewHolder {
        return HandmadeObjectListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_list_row, parent, false) as CardView)
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