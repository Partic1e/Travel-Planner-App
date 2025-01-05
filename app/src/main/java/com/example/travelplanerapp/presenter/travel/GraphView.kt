package com.example.travelplanerapp.presenter.travel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var graph: Graph? = null
    private val paintNode = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    private val paintEdge = Paint().apply {
        color = Color.GRAY
        strokeWidth = 5f
    }
    private val paintText = Paint().apply {
        color = Color.BLACK
        textSize = 30f
    }

    fun setGraphData(graph: Graph, startCity: String, endCity: String) {
        this.graph = graph
        this.startCity = startCity
        this.endCity = endCity
        invalidate()
    }

    private var startCity: String = ""
    private var endCity: String = ""

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graph?.let { graphData ->
            val positions = calculateNodePositions(graphData.nodes, startCity, endCity)

            // Рисуем рёбра
            graphData.edges.forEach { edge ->
                val start = positions[edge.from]!!
                val end = positions[edge.to]!!
                canvas.drawLine(start.x, start.y, end.x, end.y, paintEdge)
                canvas.drawText(
                    "${edge.price}₽",
                    (start.x + end.x) / 2,
                    (start.y + end.y) / 2,
                    paintText
                )
            }

            // Рисуем узлы
            positions.forEach { (city, pos) ->
                canvas.drawCircle(pos.x, pos.y, 20f, paintNode)
                canvas.drawText(city, pos.x + 25, pos.y, paintText)
            }
        }
    }

    private fun calculateNodePositions(
        nodes: List<String>, startCity: String, endCity: String
    ): Map<String, PointF> {
        val positions = mutableMapOf<String, PointF>()
        val width = width.toFloat()
        val height = height.toFloat()
        val centerY = height / 2f
        val nodeCount = nodes.size

        // Ширина между узлами
        val stepX = width / (nodeCount - 1)

        // Распределяем узлы
        for ((index, city) in nodes.withIndex()) {
            val x = stepX * index
            val y = when {
                city == startCity || city == endCity -> centerY // Начальный и конечный на одной линии
                else -> height / 4 + (index % 2) * height / 2 // Остальные распределяем равномерно
            }
            positions[city] = PointF(x, y)
        }

        return positions
    }
}
