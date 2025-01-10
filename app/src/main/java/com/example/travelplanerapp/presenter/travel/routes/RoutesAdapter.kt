package com.example.travelplanerapp.presenter.travel.routes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplanerapp.data.entity.RouteEntity
import com.example.travelplanerapp.databinding.TravelItemBinding

class RoutesAdapter : ListAdapter<RouteEntity, RoutesAdapter.RoutesViewHolder>(RouteDiffUtil()){

    class RoutesViewHolder(
        private val binding: TravelItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(routeEntity: RouteEntity) = with(binding) {
            travelId.text = routeEntity.id.toString()
            travelName.text = routeEntity.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = TravelItemBinding.inflate(inflater, parent, false)
        return RoutesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class RouteDiffUtil : DiffUtil.ItemCallback<RouteEntity>() {

        override fun areItemsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean =
            oldItem == newItem
    }
}
