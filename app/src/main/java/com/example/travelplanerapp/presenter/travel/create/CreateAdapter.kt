package com.example.travelplanerapp.presenter.travel.create

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.databinding.RouteItemBinding
import com.example.travelplanerapp.presenter.city.CityAdapter

class CreateAdapter : RecyclerView.Adapter<CreateAdapter.CreateViewHolder>() {

    private val items = mutableListOf<List<City>>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<List<City>>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RouteItemBinding.inflate(inflater, parent, false)
        return CreateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreateViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CreateViewHolder(
        binding: RouteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val cityAdapter = CityAdapter()

        init {
            binding.routeItem.apply {
                adapter = cityAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        fun bind(cities: List<City>) {
            cityAdapter.submitList(cities)
        }
    }
}
