package com.example.travelplanerapp.presenter.travel.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.databinding.FragmentListBinding
import com.example.travelplanerapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding()

    private val adapter = RouteAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.travelList) {
            adapter = this@ListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.routes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
