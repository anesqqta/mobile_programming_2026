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
        val firstColor = (squares[0].background as ColorDrawable).color

        val allSame = squares.all {
            (it.background as ColorDrawable).color == firstColor
        }
        if (allSame) {
            showRestartDialog()
        }
    }
    private fun showRestartDialog() {
        AlertDialog.Builder(this)
            .setTitle("Перемога!")
            .setMessage("Усі квадрати одного кольору.\nПочати гру спочатку?")
            .setPositiveButton("Restart") { _, _ ->
                restartGame()
            }
            .setCancelable(false)
            .show()
    }
    private fun restartGame() {
        squares.forEach { cell ->
            cell.setBackgroundColor(randomColor())
        }
    }
}