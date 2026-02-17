package com.kp.projectone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycleView = findViewById<RecyclerView>(R.id.rvProducts)

        val productList = listOf(
            Product(1, "Смартфон", "Потужний процесор та крута камера", "https://scdn.comfy.ua/89fc351a-22e7-41ee-8321-f8a9356ca351/https://cdn.comfy.ua/media/catalog/product/i/p/iphone-14-pro-finish-select-202209-6-1inch-deeppurple_1__1.jpg/w_600"),
            Product(2, "Ноутбук", "Швидкий та легкий для роботи", "https://content2.rozetka.com.ua/goods/images/big/624971169.jpg"),
            Product(3, "Навушники", "Чистий звук та шумопоглинання", "https://i.allo.ua/media/catalog/product/cache/3/image/524x494/602f0fa2c1f0d1ba5e241f914e856ff9/3/1/318173419.webp")

        )

        val adapter = ProductAdapter(productList)

        recycleView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@ProductsActivity)
            setHasFixedSize(true)
        }
    }
}

