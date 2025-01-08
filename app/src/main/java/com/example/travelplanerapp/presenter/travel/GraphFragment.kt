package com.example.travelplanerapp.presenter.travel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelplanerapp.R
import com.example.travelplanerapp.data.model.Ticket

class GraphFragment : Fragment(R.layout.fragment_graph) {

    private val viewModel: GraphViewModel by viewModels()
    private lateinit var graphView: GraphView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphView = view.findViewById(R.id.graphView)

        // Пример билетов
        val tickets = listOf(
            Ticket(1, "Moscow", "Paris", 100.0, "2025-01-10"),
            Ticket(2, "Moscow", "Amsterdam", 200.0, "2025-01-11"),
            Ticket(3, "Moscow", "Minsk", 100.0, "2025-01-11"),
            Ticket(4, "Paris", "London", 100.0, "2025-01-11"),
            Ticket(5, "Paris", "Madrid", 100.0, "2025-01-11"),
            Ticket(6, "Minsk", "London", 300.0, "2025-01-12"),
            Ticket(7, "Minsk", "Madrid", 300.0, "2025-01-12"),
            Ticket(8, "Amsterdam", "London", 300.0, "2025-01-12"),
            Ticket(9, "Amsterdam", "Madrid", 300.0, "2025-01-12"),
            Ticket(10, "Madrid", "Berlin", 300.0, "2025-01-12"),
            Ticket(11, "London", "Berlin", 300.0, "2025-01-12")
        )

        // Определяем начальный и конечный города
        val startCity = tickets.first().departureCity
        val endCity = tickets.last().arrivalCity

        // Загружаем данные в ViewModel
        viewModel.loadTickets(tickets, startCity, endCity)

        // Наблюдаем за обновлением графа и самого дешёвого пути
        viewModel.graphLiveData.observe(viewLifecycleOwner) { graph ->
            val cheapestPath = viewModel.cheapestPathLiveData.value.orEmpty()
            graphView.setGraphData(graph, startCity, endCity, cheapestPath)
        }
    }
}
