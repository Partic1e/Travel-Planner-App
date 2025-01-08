package com.example.travelplanerapp.presenter.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelplanerapp.data.model.Ticket

class GraphViewModel : ViewModel() {

    private val _graphLiveData = MutableLiveData<Graph>()
    val graphLiveData: LiveData<Graph> get() = _graphLiveData

    private val _cheapestPathLiveData = MutableLiveData<List<String>>()
    val cheapestPathLiveData: LiveData<List<String>> get() = _cheapestPathLiveData

    fun loadTickets(tickets: List<Ticket>, startCity: String, endCity: String) {
        // Создаём список узлов и рёбер
        val nodes = tickets.flatMap { listOf(it.departureCity, it.arrivalCity) }.distinct()
        val edges = tickets.map { GraphEdge(it.departureCity, it.arrivalCity, it.price) }
        val graph = Graph(nodes, edges)
        _graphLiveData.value = graph

        // Вычисляем самый дешевый путь
        val cheapestPath = findCheapestPath(nodes, edges, startCity, endCity)
        _cheapestPathLiveData.value = cheapestPath
    }

    private fun findCheapestPath(
        nodes: List<String>,
        edges: List<GraphEdge>,
        startCity: String,
        endCity: String
    ): List<String> {
        // Построение графа
        val graph = mutableMapOf<String, MutableList<Pair<String, Double>>>()
        edges.forEach { edge ->
            graph.getOrPut(edge.from) { mutableListOf() }.add(edge.to to edge.price)
            graph.getOrPut(edge.to) { mutableListOf() }.add(edge.from to edge.price)
        }

        // Инициализация данных для алгоритма Дейкстры
        val distances = nodes.associateWith { Double.MAX_VALUE }.toMutableMap()
        val previous = mutableMapOf<String, String?>()
        val unvisited = nodes.toMutableSet()
        distances[startCity] = 0.0

        // Алгоритм Дейкстры
        while (unvisited.isNotEmpty()) {
            val current = unvisited.minByOrNull { distances[it] ?: Double.MAX_VALUE } ?: break
            unvisited.remove(current)

            if (current == endCity) break

            graph[current]?.forEach { (neighbor, weight) ->
                val newDistance = distances[current]!! + weight
                if (newDistance < distances[neighbor]!!) {
                    distances[neighbor] = newDistance
                    previous[neighbor] = current
                }
            }
        }

        // Восстановление пути
        val path = mutableListOf<String>()
        var current: String? = endCity
        while (current != null) {
            path.add(current)
            current = previous[current]
        }
        return path.reversed()
    }
}

data class Graph(val nodes: List<String>, val edges: List<GraphEdge>)
data class GraphEdge(val from: String, val to: String, val price: Double)
