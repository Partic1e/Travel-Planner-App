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
            Ticket(1, "Moscow", "Paris", 500.0, "2025-01-10"),
            Ticket(2, "Moscow", "Amsterdam", 200.0, "2025-01-11"),
            Ticket(4, "Paris", "London", 200.0, "2025-01-11"),
            Ticket(3, "Amsterdam", "Berlin", 300.0, "2025-01-12"),
            Ticket(5, "London", "Berlin", 300.0, "2025-01-12")
        )
        viewModel.loadTickets(tickets)

        viewModel.graphLiveData.observe(viewLifecycleOwner) { graph ->
            // Определяем startCity и endCity из списка билетов
            val startCity = tickets.first().departureCity
            val endCity = tickets.last().arrivalCity

            graphView.setGraphData(
                graph = graph,
                startCity = startCity,
                endCity = endCity
            )
        }
    }
}
