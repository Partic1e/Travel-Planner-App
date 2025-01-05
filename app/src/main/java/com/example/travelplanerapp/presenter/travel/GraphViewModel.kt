package com.example.travelplanerapp.presenter.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelplanerapp.data.model.Ticket

class GraphViewModel : ViewModel() {

    private val _graphLiveData = MutableLiveData<Graph>()
    val graphLiveData: LiveData<Graph> get() = _graphLiveData

    fun loadTickets(tickets: List<Ticket>) {
        val nodes = tickets.flatMap { listOf(it.departureCity, it.arrivalCity) }.distinct()
        val edges = tickets.map { GraphEdge(it.departureCity, it.arrivalCity, it.price) }
        _graphLiveData.value = Graph(nodes, edges)
    }
}

data class Graph(val nodes: List<String>, val edges: List<GraphEdge>)
data class GraphEdge(val from: String, val to: String, val price: Double)
