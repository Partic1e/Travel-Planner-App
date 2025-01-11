package com.example.travelplanerapp.presenter.graph

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class GraphFragment : Fragment(R.layout.fragment_graph) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: GraphViewModel by viewModels {viewModelFactory}
    private lateinit var graphView: GraphView

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graphView = view.findViewById(R.id.graphView)


        val routeId = GraphFragmentArgs.fromBundle(requireArguments()).routeId
        viewModel.getTickets(routeId)
        viewModel.tickets.observe(viewLifecycleOwner) { tickets ->
            if (!tickets.isNullOrEmpty()) {
                // Определяем начальный и конечный города
                val startCity = tickets.first().startCity
                val endCity = tickets.last().endCity

                // Загружаем билеты и строим граф
                viewModel.loadTickets(tickets, startCity, endCity)

                // Наблюдаем за изменением графа и самого дешевого пути
                viewModel.graphLiveData.observe(viewLifecycleOwner) { graph ->
                    val cheapestPath = viewModel.cheapestPathLiveData.value.orEmpty()
                    graphView.setGraphData(graph, startCity, endCity, cheapestPath)
                }
            } else {
                // Обработка случая, когда билеты отсутствуют
                Toast.makeText(requireContext(), "Tickets not found for route", Toast.LENGTH_SHORT).show()
            }
        }

        // Наблюдаем за обновлением графа и самого дешёвого пути

    }
}