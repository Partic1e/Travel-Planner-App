package com.example.travelplanerapp.presenter.city

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.databinding.FragmentCityBinding

class CityFragment : Fragment(R.layout.fragment_city) {

    private val binding: FragmentCityBinding by viewBinding()
    private val adapter = CityAdapter()
    private val cityList = mutableListOf<City>()
    private var selectedDate: String? = null

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.cityArray) {
            adapter = this@CityFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.date.setOnClickListener {
            showDatePicker()
        }

        binding.addCityButton.setOnClickListener {
            addCity()
        }

        binding.completeButton.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelableArray("cityList", cityList.toTypedArray())
            }
            parentFragmentManager.setFragmentResult("requestKey", bundle)

            val destination = CityFragmentDirections.actionCityFragmentToCreateFragment()
            findNavController().navigate(destination)
        }
    }

    private fun addCity() {
        val cityName = binding.cityEditText.text.toString().trim()

        if (cityName.isEmpty() && selectedDate == null) {
            Toast.makeText(requireContext(), "Введите город и дату", Toast.LENGTH_SHORT).show()
            return
        }

        val city = City(cityName, selectedDate!!)
        cityList.add(city)
        adapter.submitList(cityList.toList())
        binding.cityEditText.text.clear()
        selectedDate = null
        Toast.makeText(requireContext(), "Город добавлен", Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay.${selectedMonth + 1}.$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
