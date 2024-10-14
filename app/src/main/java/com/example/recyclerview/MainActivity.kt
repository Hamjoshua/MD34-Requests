package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.graphics.ColorSpace.Model
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rView: RecyclerView = findViewById(R.id.rView)
        rView.layoutManager = LinearLayoutManager(this)
        rView.adapter = Adapter(this, updateList())
    }

    private fun updateList() : ArrayList<ColorData> {
        val list = arrayListOf<ColorData>()

        list.add(
            ColorData(
            "RED", Color.RED
            )
        )

        list.add(
            ColorData(
                "BLUE", Color.BLUE
            )
        )

        list.add(
            ColorData(
                "GREEN", Color.GREEN
            )
        )

        return list;
    }
}

data class ColorData(val colorName: String, val colorHex: Int){

}