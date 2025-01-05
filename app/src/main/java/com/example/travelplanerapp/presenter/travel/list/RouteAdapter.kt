package com.example.travelplanerapp.presenter.travel.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelplanerapp.data.model.Route
import com.example.travelplanerapp.databinding.TravelItemBinding

class RouteAdapter : ListAdapter<Route, RouteAdapter.RouteViewHolder>(RouteDiffUtil()){

    class RouteViewHolder(
        private val binding: TravelItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(route: Route) = with(binding) {
            travelId.text = route.id.toString()
            travelName.text = route.name
            travelSum.text = route.sum.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = TravelItemBinding.inflate(inflater, parent, false)
        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class RouteDiffUtil : DiffUtil.ItemCallback<Route>() {

        override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean =
            oldItem == newItem
    }
}
