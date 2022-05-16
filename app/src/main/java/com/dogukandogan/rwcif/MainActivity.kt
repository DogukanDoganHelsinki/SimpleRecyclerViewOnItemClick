package com.dogukandogan.rwcif

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dogukandogan.rwcif.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener{

    lateinit var binding: ActivityMainBinding
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        binding.insertBtn.setOnClickListener {
            insertItem(view)
        }

        binding.deleteBtn.setOnClickListener {
            removeItem(view)
        }
        generateDummyList(500)
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)

        val newItem = ExampleItem(
            R.drawable.ic_baseline_accessibility_new_24,
            "New item at position $index",
            "Line 2"
        )

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem:ExampleItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_two_wheeler_24
                1 -> R.drawable.ic_baseline_tour_24
                else -> R.drawable.ic_baseline_tsunami_24
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}