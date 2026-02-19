package com.kp.projectone

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LifeCyclesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_life_cycles)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("Lifecycle", "Метод onCreate викликано")

        val btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener {
            finish()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Метод onStart викликано")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Метод onResume викликано")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Метод onPause викликано")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Метод onStop викликано")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Метод onDestroy викликано")
    }
}













