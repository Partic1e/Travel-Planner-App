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
    private val paintEdgeHighlight = Paint().apply {
        color = Color.GREEN
        strokeWidth = 7f
    }
    private val paintText = Paint().apply {
        color = Color.BLACK
        textSize = 30f
    }
    private val paintBorder = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private var startCity: String = ""
    private var endCity: String = ""
    private var cheapestPath: List<String> = emptyList()

    fun setGraphData(graph: Graph, startCity: String, endCity: String, cheapestPath: List<String>) {
        this.graph = graph
        this.startCity = startCity
        this.endCity = endCity
        this.cheapestPath = cheapestPath
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Рисуем рамку вокруг графа
        drawBorder(canvas)

        graph?.let { graphData ->
            val positions = calculateNodePositions(graphData.nodes, startCity, endCity)

            // Рисуем рёбра
            graphData.edges.forEach { edge ->
                val start = positions[edge.from]!!
                val end = positions[edge.to]!!

                // Проверяем, принадлежит ли ребро самому дешевому пути
                val isHighlighted = cheapestPath.zipWithNext()
                    .any { it.first == edge.from && it.second == edge.to }

                val edgePaint = if (isHighlighted) paintEdgeHighlight else paintEdge
                canvas.drawLine(start.x, start.y, end.x, end.y, edgePaint)

                // Подписываем стоимость на рёбрах
                canvas.drawText(
                    "${edge.price.toInt()}₽",
                    (start.x + end.x) / 2,
                    (start.y + end.y) / 2,
                    paintText
                )
            }

            // Рисуем узлы
            positions.forEach { (city, pos) ->
                canvas.drawCircle(pos.x, pos.y, 20f, paintNode)

                // Проверка, чтобы текст не выходил за правый край
                val textX = if (pos.x + 100 > width) pos.x - 100 else pos.x + 25
                canvas.drawText(city, textX, pos.y, paintText)
            }
        }
    }

    private fun drawBorder(canvas: Canvas) {
        val margin = 20f
        val rect = android.graphics.RectF(
            margin,
            margin,
            width - margin,
            height - margin
        )
        canvas.drawRoundRect(rect, 30f, 30f, paintBorder) // Закруглённая рамка
    }

    private fun calculateNodePositions(
        nodes: List<String>, startCity: String, endCity: String
    ): Map<String, PointF> {
        val positions = mutableMapOf<String, PointF>()
        val width = width.toFloat()
        val height = height.toFloat()
        val margin = 40f // Отступ от рамки
        val availableWidth = width - 2 * margin
        val availableHeight = height - 2 * margin

        // Разделение узлов на уровни
        val levels = createNodeLevels(graph!!.edges, startCity, endCity)

        // Распределение уровней слева направо
        val stepX = availableWidth / (levels.size - 1)
        levels.forEachIndexed { levelIndex, levelNodes ->
            val levelX = margin + stepX * levelIndex
            val stepY = availableHeight / (levelNodes.size + 1) // Распределение по вертикали

            levelNodes.forEachIndexed { nodeIndex, city ->
                val y = margin + stepY * (nodeIndex + 1)
                positions[city] = PointF(levelX, y)
            }
        }

        return positions
    }

    private fun createNodeLevels(
        edges: List<GraphEdge>, startCity: String, endCity: String
    ): List<List<String>> {
        val levels = mutableListOf<List<String>>()
        val visited = mutableSetOf(startCity)

        // Начальный город
        levels.add(listOf(startCity))

        // Построение уровней
        var currentLevel = listOf(startCity)
        while (currentLevel.isNotEmpty() && !visited.contains(endCity)) {
            val nextLevel = edges
                .filter { it.from in currentLevel && it.to !in visited }
                .map { it.to }
                .distinct()
            visited.addAll(nextLevel)
            if (nextLevel.isNotEmpty()) levels.add(nextLevel)
            currentLevel = nextLevel
        }

        // Конечный город
        levels.add(listOf(endCity))
        return levels
    }
}
