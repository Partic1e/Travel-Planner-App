package com.example.travelplanerapp.presenter.travel.create

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.data.model.RouteParam
import com.example.travelplanerapp.databinding.FragmentCreateBinding
import com.example.travelplanerapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class CreateFragment : Fragment(R.layout.fragment_create) {

    private val binding: FragmentCreateBinding by viewBinding()
    private val adapter = CreateAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CreateViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.ticketList) {
            adapter = this@CreateFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        observeViewModel()

        binding.addCitiesButton.setOnClickListener {
            val action = CreateFragmentDirections.actionCreateFragmentToCityFragment(viewModel.route.value.orEmpty().toTypedArray())
            findNavController().navigate(action)
        }

        binding.createRouteButton.setOnClickListener {
            viewModel.addStartCity(binding.startCity.text.toString())
            viewModel.createRoute(binding.routeName.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.route.observe(viewLifecycleOwner) {
            adapter.submitList(it.map { routeParam -> routeParam.cities })
        }

        viewModel.ticketsSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                viewModel.routeId.observe(viewLifecycleOwner) { routeId ->
                    val action = CreateFragmentDirections.actionCreateFragmentToGraphFragment(routeId)
                    findNavController().navigate(action)
                }
            }
        }

        parentFragmentManager.setFragmentResultListener("updatedRoute", this) { _, bundle ->
            val updatedRoute = bundle.getParcelableArrayList<RouteParam>("routeList").orEmpty()
            if (updatedRoute.isNotEmpty()) {
                viewModel.updateRoute(updatedRoute)
            }
        }
    }
}
