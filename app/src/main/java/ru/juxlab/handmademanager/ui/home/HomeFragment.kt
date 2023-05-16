package ru.juxlab.handmademanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import ru.juxlab.handmademanager.HandmadeManagerApplication
import ru.juxlab.handmademanager.R
import ru.juxlab.handmademanager.ui.edit_hmobject.HandmadeObjectViewModel
import ru.juxlab.handmademanager.ui.edit_hmobject.HandmadeObjectViewModelFactory

class HomeFragment: Fragment(), KodeinAware {


    override lateinit var kodein: Kodein

    private val viewModelFactory: HomeViewModelFactory by instance()

    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        kodein = (requireActivity().application as HandmadeManagerApplication).kodein

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //Recycler view
        val handmadeObjectsRecyclerView = root.findViewById<RecyclerView>(R.id.recyclerView_objects_list)
        val adapter = HandmadeObjectListAdapter(viewModel)
        handmadeObjectsRecyclerView.adapter = adapter
        handmadeObjectsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.handmadeObjectsData.observe(viewLifecycleOwner, Observer { adapter.setData(it) })

        return root
    }
}


