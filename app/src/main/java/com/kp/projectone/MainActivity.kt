package com.kp.projectone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvHelloWorld = findViewById<TextView>(R.id.tvHelloWorld)
        val tvThisIs = findViewById<TextView>(R.id.tvThisIs)
        val btnNextActivity = findViewById<Button>(R.id.btnNextActivity)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnProducts = findViewById<Button>(R.id.btnProducts)
        val btnlifeCycles = findViewById<Button>(R.id.btnlifeCycles)
        val btnColor = findViewById<Button>(R.id.btnColor)

        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (result.resultCode){
                RESULT_OK -> {
                    val data: Intent? = result.data
                    val message = data?.getStringExtra("my_key")
                    tvHelloWorld.text = "hello $message"
                }
                RESULT_CANCELED -> {
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                }
            }
        }
        tvThisIs.setOnClickListener {
            if (tvHelloWorld.currentTextColor == getColor(R.color.red)) {
                tvHelloWorld.setTextColor(getColor(R.color.white))
            } else {
                tvHelloWorld.setTextColor(getColor(R.color.red))
            }
        }
        btnNextActivity.setOnClickListener{
            val intent = (Intent(this, SecondActivity::class.java))
            intent.putExtra("main", "test")
            startActivity(intent)
        }
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            getResult.launch(intent)
        }
        btnProducts.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            getResult.launch(intent)
        }
        btnlifeCycles.setOnClickListener {
            val intent = Intent(this, LifeCyclesActivity::class.java)
            startActivity(intent)
        }
        btnColor.setOnClickListener {
            val intent = Intent(this, ColorActivity::class.java)
            startActivity(intent)
        }


        onBackPressedDispatcher.addCallback(
            owner = this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    showAlert(
                        close = {
                            finish()
                        },
                    )
                }
            }
        )
    }
    fun showAlert(close: () -> Unit, size: Int? = null){
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle("Close app?")
        builder.setMessage("description")

        size?.let { sizeIt ->
            Log.d("size", sizeIt.toString())
        } ?: run {
            Log.d("size", "size is null")
        }
        builder.setPositiveButton("Discard") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setNegativeButton("Close") { dialog, _ ->
            close()
        }
        builder.show()
    }
}
