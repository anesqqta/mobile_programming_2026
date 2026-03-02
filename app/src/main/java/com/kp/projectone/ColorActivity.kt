package com.kp.projectone

import android.os.Bundle
import android.widget.TextView
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog

class ColorActivity : AppCompatActivity() {

    private lateinit var squares: List<TextView>
    private var level = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        squares = listOf(
            findViewById(R.id.square1),
            findViewById(R.id.square2),
            findViewById(R.id.square3),
            findViewById(R.id.square4),
            findViewById(R.id.square5),
            findViewById(R.id.square6),
            findViewById(R.id.square7),
            findViewById(R.id.square8),
            findViewById(R.id.square9),
            findViewById(R.id.square10),
            findViewById(R.id.square11),
            findViewById(R.id.square12),
            findViewById(R.id.square13),
            findViewById(R.id.square14),
            findViewById(R.id.square15)
        )
        restartGame()

        squares.forEach { cell ->
            cell.setOnClickListener {
                changeColor(cell)
                checkWin()
            }
        }
    }
    private fun randomColor(): Int {
        val colors = listOf(Color.RED, Color.YELLOW, Color.GREEN)
        return colors.random()
    }
    private fun changeColor(cell: TextView) {
        val currentColor = (cell.background as ColorDrawable).color

        val nextColor = when (currentColor) {
            Color.RED -> Color.YELLOW
            Color.YELLOW -> Color.GREEN
            else -> Color.RED
        }
        cell.setBackgroundColor(nextColor)
    }
    private fun checkWin() {
        val win = when(level) {
            1 -> checkLevel1()
            2 -> checkLevel2()
            3 -> checkLevel3()
            4 -> checkLevel4()
            else -> false
        }
        if (win) {
            showLevelDialog()
        }
    }
    private fun checkLevel1(): Boolean {
        val firstColor = (squares[0].background as ColorDrawable).color
        return squares.all {
            (it.background as ColorDrawable).color == firstColor
        }
    }
    private fun checkLevel2(): Boolean {
        for (i in squares.indices) {
            val column = i % 3
            val color = (squares[i].background as ColorDrawable).color
            when(column) {
                0 -> if (color != Color.YELLOW) return false
                1 -> if (color != Color.RED) return false
                2 -> if (color != Color.GREEN) return false
            }
        }
        return true
    }
    private fun checkLevel3(): Boolean {
        for (row in 0 until 5) {
            var redCount = 0
            for (col in 0 until 3) {
                val index = row * 3 + col
                val color = (squares[index].background as ColorDrawable).color
                if (color == Color.RED) {
                    redCount++
                }
            }
            if (redCount != 1) return false
        }
        return true
    }
    private fun checkLevel4(): Boolean {
        for (i in squares.indices) {
            val currentColor = (squares[i].background as ColorDrawable).color
            if (i % 3 != 2) {
                val rightColor = (squares[i + 1].background as ColorDrawable).color
                if (currentColor == rightColor) return false
            }
            if (i < 12) {
                val bottomColor = (squares[i + 3].background as ColorDrawable).color
                if (currentColor == bottomColor) return false
            }
        }
        return true
    }
    private fun showLevelDialog() {
        AlertDialog.Builder(this)
            .setTitle("Рівень $level пройдено!")
            .setMessage("Що робимо далі?")
            .setPositiveButton("Наступний рівень") { _, _ ->
                if (level < 4) {
                    level++
                    restartGame()
                }
            }
            .setNegativeButton("Спочатку") { _, _ ->
                level = 1
                restartGame()
            }
            .setNeutralButton("Вийти") { _, _ ->
                finish()
            }
            .show()
    }
    private fun restartGame() {
        squares.forEach { cell ->
            cell.setBackgroundColor(randomColor())
        }
    }
}