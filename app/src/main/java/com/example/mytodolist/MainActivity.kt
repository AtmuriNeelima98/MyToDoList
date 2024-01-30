package com.example.mytodolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoRecyclerview = findViewById<RecyclerView>(R.id.toDoRecyclerview)
        val addButton = findViewById<AppCompatButton>(R.id.addBtn)
        val text = findViewById<TextInputEditText>(R.id.edit_text)
        val context: Context = this

        toDoRecyclerview.layoutManager = LinearLayoutManager(this)

        var data = ArrayList<ToDoItemModel>()
        val toDoList: List<String> = mutableListOf("Buying Vegetables", "Playing Games")
        for (i in toDoList.indices)
            data.add(ToDoItemModel("$i", toDoList[i]))

        val toDoAdapter = ToDoItemAdapter(data, context)
        toDoRecyclerview.adapter = toDoAdapter

        addButton.setOnClickListener {
            val rnds = (0..50).random()

            val textValue = text.text.toString()

            if (textValue.isEmpty()) {
                Toast.makeText(this, "Please Enter To Do Item", Toast.LENGTH_SHORT).show()
            } else {
                data.add(ToDoItemModel("$rnds", textValue))
                toDoAdapter.notifyDataSetChanged()

                text.text?.clear()
            }
        }
    }
}