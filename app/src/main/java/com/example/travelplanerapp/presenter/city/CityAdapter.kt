package com.example.travelplanerapp.presenter.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplanerapp.data.model.City
import com.example.travelplanerapp.databinding.CityItemBinding

class CityAdapter : ListAdapter<City, CityAdapter.CityViewHolder>(CityDiffUtil()) {

    class CityViewHolder(
        private val binding: CityItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) = with(binding) {
            cityName.text = city.city
            cityDate.text = city.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CityItemBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class CityDiffUtil : DiffUtil.ItemCallback<City>() {

        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem.city == newItem.city

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem == newItem
    }
}
