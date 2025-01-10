package com.example.travelplanerapp.presenter.travel.create

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.data.model.City
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

        getCities()

        viewModel.route.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }

        binding.addCitiesButton.setOnClickListener {
            val destination = CreateFragmentDirections.actionCreateFragmentToCityFragment()
            findNavController().navigate(destination)
        }

        binding.createRouteButton.setOnClickListener {
            viewModel.addStartCity(binding.startCity.text.toString())
            viewModel.createRoute(binding.routeName.text.toString())
        }

    }

    private fun getCities() {
        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val cities = bundle.getParcelableArray("cityList")?.map { it as City }

            if (cities!!.isNotEmpty()) {
                viewModel.addCities(cities.toList())
            }
        }
    }
}
